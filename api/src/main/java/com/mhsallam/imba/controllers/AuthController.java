package com.mhsallam.imba.controllers;

import com.mhsallam.imba.models.dto.LoginDto;
import com.mhsallam.imba.models.dto.JwtDto;
import com.mhsallam.imba.security.TokenUtil;
import com.mhsallam.imba.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtDto login(@RequestBody LoginDto loginDto) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(loginDto.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        return new JwtDto(token);
    }

    @PostMapping(value = "/refresh")
    @ResponseStatus(HttpStatus.OK)
    public JwtDto refresh(@RequestHeader(value = "${auth.header}") String token) {
        return new JwtDto(userService.refreshToken(token));
    }
}