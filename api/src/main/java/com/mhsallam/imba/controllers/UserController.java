package com.mhsallam.imba.controllers;

import com.mhsallam.imba.models.dto.UserDto;
import com.mhsallam.imba.models.entity.User;
import com.mhsallam.imba.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/info")
    public ResponseEntity<UserDto> info() {
        User user = userRepository.findByEmail(this.getUsername());
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserDto> typeMap = modelMapper.createTypeMap(User.class, UserDto.class);
        typeMap.addMapping(User::getUsername, UserDto::setEmail);
        UserDto result  = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}