package com.mhsallam.imba.controllers;

import com.mhsallam.imba.error.ConflictException;
import com.mhsallam.imba.error.NotFoundException;
import com.mhsallam.imba.models.dto.UserDto;
import com.mhsallam.imba.models.entity.User;
import com.mhsallam.imba.repositories.UserRepository;
import com.mhsallam.imba.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController extends BaseController {

    @Value("${env.development}")
    private boolean developmentMode;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<User> create(@RequestBody UserDto userDto) throws ConflictException, Exception {
        // isEnabled is set to developmentMode
        User result = userService.register(userDto, developmentMode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody UserDto userDto) throws ConflictException, Exception {
        User result = userService.update(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}