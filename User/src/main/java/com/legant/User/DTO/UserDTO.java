package com.legant.User.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String password;
    private String cartID;

    private Date createdAt;
    private Date updatedAt;

}
