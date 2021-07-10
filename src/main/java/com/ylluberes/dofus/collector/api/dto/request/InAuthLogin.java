package com.ylluberes.dofus.collector.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class InAuthLogin {
    @NotBlank(message = "provide a username or email")
    private final String userName;

    @NotBlank(message = "provide a password")
    private final String password;

}
