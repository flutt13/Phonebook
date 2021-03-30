package com.flutt13.phonebook.controller;

import com.flutt13.phonebook.entities.dto.UserDto;
import com.flutt13.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public UserDto addUser(UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping
    @ResponseBody
    public List<UserDto> searchUsersByName(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{userName}")
    @ResponseBody
    public UserDto getUser(@PathVariable("userName") String userName) {
        return userService.getUser(userName);
    }

    @DeleteMapping("{userName}")
    @ResponseBody
    public void deleteUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }

    @PatchMapping("{userName}")
    @ResponseBody
    public UserDto editUser(@PathVariable("userName") String userName, UserDto userDto) {
        return userService.editUser(userName, userDto);
    }
}
