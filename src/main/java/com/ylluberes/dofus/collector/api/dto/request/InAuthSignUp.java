package com.ylluberes.dofus.collector.api.dto.request;

import com.ylluberes.dofus.collector.api.dto.request.constraints.PasswordConfirmConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@PasswordConfirmConstraint(message = "password and confirmed password should match")
public class InAuthSignUp implements Serializable {

    @NotBlank(message = "provide a userName")
    @Length(max = 25, min = 5, message = "userName size should be between 5 and 25 inclusive")
    private final String userName;

    @NotBlank(message = "provide a password")
    private final String password;

    private final String confirmPassword;

    @Email(message = "Enter a valid email address")
    @NotBlank(message = "email address should not be empty")
    private final String email;
}
