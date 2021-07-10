package com.ylluberes.dofus.collector.api.controllers;

import com.ylluberes.dofus.collector.api.domain.User;
import com.ylluberes.dofus.collector.api.dto.request.InAuthLogin;
import com.ylluberes.dofus.collector.api.dto.request.InAuthSignUp;
import com.ylluberes.dofus.collector.api.dto.responses.GenericResponse;
import com.ylluberes.dofus.collector.api.jwt.JwtProvider;
import com.ylluberes.dofus.collector.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dofus-collector/api/v1/auth/")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<GenericResponse> signUp(@Valid @RequestBody InAuthSignUp inAuthSignUp) {
        User user = new User();
        user.setUsername(inAuthSignUp.getUserName());
        user.setEmail(inAuthSignUp.getEmail());
        user.setPassword(encoder.encode(inAuthSignUp.getPassword()));
        GenericResponse<User> genericResponse = userService.saveOrUpdate(user);
        return new ResponseEntity<>(genericResponse, genericResponse.getServerStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@Valid @RequestBody InAuthLogin inAuthLogin) {
        GenericResponse response = userService.login(inAuthLogin);
        return new ResponseEntity<>(response,response.getServerStatus());
    }

}
