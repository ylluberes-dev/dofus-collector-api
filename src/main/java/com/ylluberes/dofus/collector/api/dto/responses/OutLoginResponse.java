package com.ylluberes.dofus.collector.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OutLoginResponse {

    private final String tokenKey;
    private final String username;

}
