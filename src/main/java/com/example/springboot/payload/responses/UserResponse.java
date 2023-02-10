package com.example.springboot.payload.responses;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String phone;
    private String avatar;
    private String createdAt;
}
