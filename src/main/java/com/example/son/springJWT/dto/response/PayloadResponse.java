package com.example.son.springJWT.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadResponse {
    private Integer userId;
    private String email;
    private String role;
    private Integer iat;
    private Integer exp;
}
