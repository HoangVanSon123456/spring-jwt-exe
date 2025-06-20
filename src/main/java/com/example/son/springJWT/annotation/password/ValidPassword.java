package com.example.son.springJWT.annotation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPasswordValidator.class)
@Target({ElementType.FIELD}) // app dung voi truong nao
@Retention(RetentionPolicy.RUNTIME) // xuat hien o thoi diem nao => co the lay vi du Lombok cho case complier
public @interface ValidPassword {
    String message() default "Invalid password format";
    Class<?>[] groups() default {}; // group kieu action
    Class<? extends Payload>[] payload() default {}; // metadata
}
