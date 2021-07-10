package com.ylluberes.dofus.collector.api.config;

import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.util.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
       // response.sendRedirect("https://www.google.es/.");
       // response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not able to access this resource, please sign");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        GenericResponse<String> errorResponse = new GenericResponse<>();
        errorResponse.setData(null);
        errorResponse.setMessage(authException.getMessage());
        errorResponse.setSuccess(false);
        errorResponse.setServerStatus(HttpStatus.FORBIDDEN);
        response.getOutputStream().println(Utils.toJson(errorResponse));
    }
}
