package com.legant.User.Helper;

import com.legant.User.DTO.UserDTO;
import com.legant.User.Model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Builders {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User userDTOToUser(UserDTO userDTO) {
        return User.builder()
                .dob(userDTO.getDob())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .build();
    }
}
