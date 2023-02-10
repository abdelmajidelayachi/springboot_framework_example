package com.example.springboot.services;

import com.example.springboot.entities.User;
import com.example.springboot.payload.responses.UserResponse;
import com.example.springboot.payload.requests.UserRequest;
import com.example.springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Abdelmajid EL AYACHI
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    /**
     * @return List of all users
     */
    public List<UserResponse> findAllUser() {
        List<UserResponse> userResponses = new ArrayList<>();
        userRepository.findAll().forEach(
                user -> userResponses.add(
                        UserResponse.builder()
                                .email(user.getEmail())
                                .firstname(user.getFirstname())
                                .lastname(user.getLastname())
                                .build()
                )
        );
        return userResponses;
    }

    /**
     * @param id
     * @return User by id
     */
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponse.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }

    /**
     * @param userRequest
     * @return User
     */
    public UserResponse saveUser(UserRequest userRequest) {
        if (userRequest.getFirstname() == null || userRequest.getLastname() == null || userRequest.getEmail() == null || userRequest.getPassword() == null || userRequest.getConfirmPassword() == null) {
            throw new IllegalArgumentException("Firstname, lastname, email, password and confirm password  are required");
        }
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and confirm password must be the same");
        }
    User user = User.builder()
                    .firstname(userRequest.getFirstname())
                    .lastname(userRequest.getLastname())
                    .email(userRequest.getEmail())
                    .password(userRequest.getPassword())
                    .build();
        User savedUser = userRepository.save(user);
        return UserResponse.builder()
                .email(savedUser.getEmail())
                .firstname(savedUser.getFirstname())
                .lastname(savedUser.getLastname())
                .build();
    }

    /**
     * delete user by id
     *
     * @param id
     * @return void
     */
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    /**
     * update user by id
     *
     * @param userRequest
     * @param id
     * @return User
     */
    @Transactional
    public UserResponse updateUser(UserRequest userRequest, Long id) {
        Optional<User> userToUpdate = userRepository.findById(id);
        if (userToUpdate.isPresent()) {
            User user = userToUpdate.get();
            if (userRequest.getFirstname() != null) {
                user.setFirstname(userRequest.getFirstname());
            }
            if (userRequest.getLastname() != null) {
                user.setLastname(userRequest.getLastname());
            }
            if (userRequest.getEmail() != null) {
                user.setEmail(userRequest.getEmail());
            }
            if (userRequest.getPassword() != null) {
                user.setPassword(userRequest.getPassword());
            }
            if (userRequest.getPhone() != null) {
                user.setTel(userRequest.getPhone());
            }
            if (userRequest.getUsername() != null) {
                user.setUsername(userRequest.getUsername());
            }

            return UserResponse.builder()
                    .email(user.getEmail())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .build();

        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * @return User
     */

    public User findUserByEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
