package com.flutt13.phonebook.service;

import com.flutt13.phonebook.entities.dto.UserDto;
import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.exception.EntityNotFoundException;
import com.flutt13.phonebook.exception.EntityExistsException;
import com.flutt13.phonebook.exception.EntityNotValidException;
import com.flutt13.phonebook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhonebookEntryService phonebookEntryService;

    User findUser(String userName) {
        User user = userRepo.findByUserName(userName);
        if (user == null) throw new EntityNotFoundException();
        return user;
    }

    public List<UserDto> searchUsersByName(String name) {
        List<User> users = userRepo.searchByName(name);
        if (users.isEmpty()) throw new EntityNotFoundException();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(UserDto.UserToUserDto(user));
        }
        return usersDto;
    }

    public UserDto addUser(UserDto userDto) {
        UserDto.validateUserDto(userDto);
        User user = UserDto.UserDtoToUser(userDto);
        if (user.getUserName() == null) user.setUserName(User.generateUserName());
        if (userRepo.findByUserName(user.getUserName()) != null) throw new EntityExistsException();
        try {
            userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotValidException();
        }
        return UserDto.UserToUserDto(user);
    }

    public UserDto getUser(String userName) {
        User user = findUser(userName);
        return UserDto.UserToUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) throw new EntityNotFoundException();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(UserDto.UserToUserDto(user));
        }
        return usersDto;
    }

    @Transactional
    public void deleteUser(String userName) {
        User user = findUser(userName);
        phonebookEntryService.deleteUserEntries(user);
        userRepo.deleteByUserName(userName);
    }

    public UserDto editUser(String userName, UserDto userDto) {
        User user = findUser(userName);
        User updated = UserDto.UpdateUserFromDto(user, userDto);
        userRepo.save(updated);
        return UserDto.UserToUserDto(updated);
    }

}
