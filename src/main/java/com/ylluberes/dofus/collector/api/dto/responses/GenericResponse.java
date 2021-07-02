package com.ylluberes.dofus.collector.api.dto.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class GenericResponse<T> implements Serializable {

    private String message;
    private boolean success;
    private HttpStatus serverStatus;
    private T data;

}