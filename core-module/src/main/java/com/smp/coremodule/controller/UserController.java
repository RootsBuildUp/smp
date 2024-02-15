package com.smp.coremodule.controller;

import com.smp.coremodule.constant.AppConstant;
import com.smp.coremodule.dto.UserDto;
import com.smp.coremodule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto ) {
        return userService.crate( userDto );
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto ) {
        return userService.update( id, userDto );
    }

    @GetMapping("/{id}")
    @Cacheable( key = "#id", value = AppConstant.USER_REDIS_NAME )
    public UserDto getById(@PathVariable Long id ) {
        return userService.getById( id );
    }

    @GetMapping("/all")
    public List<UserDto> getAllProducts() {
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    @CacheEvict( key = "#id", value = AppConstant.USER_REDIS_NAME )
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK );
    }
}
