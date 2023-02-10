package com.example.springboot.entities;


import com.example.springboot.entities.types.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "tel", length = 20)
    private String tel;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "avatar", insertable = false, columnDefinition = "varchar(255) default 'https://res.cloudinary.com/mypet-api-images/image/upload/v1673537084/ibjv0dfbpqwgwneua663.png'")
    private String avatar;

    // created_at
    @Column(name = "created_at", insertable = false, columnDefinition = "timestamp default current_timestamp")
    private String createdAt;

    @Transient
    private String formattedCreatedAt;

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(this.createdAt, formatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy");
            return dateTime.format(outputFormatter);

        } catch (Exception e) {
            return "";
        }
    }

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
