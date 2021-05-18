package com.ylluberes.dofus_collector_api.dto.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class Response<T> implements Serializable {

    private T data;
    private String message;
    private boolean success;
    private HttpStatus serverStatus;

}