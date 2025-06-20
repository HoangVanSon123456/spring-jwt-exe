package com.example.son.springJWT.dto;

import com.example.son.springJWT.annotation.email.ValidEmail;
import com.example.son.springJWT.annotation.password.ValidPassword;
import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String code;

    private String userName;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
