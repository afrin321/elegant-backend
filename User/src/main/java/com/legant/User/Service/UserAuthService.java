package com.legant.User.Service;

import com.legant.User.DTO.UserDTO;
import com.legant.User.Helper.Builders;
import com.legant.User.Model.User;
import com.legant.User.Repo.UserRepo;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    private UserRepo userRepo;
    public String signUp(UserDTO userDTO) {
        Optional<User> existingUser = userRepo.findByEmail(userDTO.getEmail());

        if (existingUser.isEmpty()) {
            userRepo.save(Builders.userDTOToUser(userDTO));
            return "User saved successfully";
        } else {
            return "User already exists";
        }
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            User userInfo = user.get();

            return UserDTO.builder()
                    .firstName(userInfo.getFirstName())
                    .lastName(userInfo.getLastName())
                    .email(userInfo.getEmail())
                    .id(userInfo.getId())
                    .build();
        }

        return null;

    }
}
