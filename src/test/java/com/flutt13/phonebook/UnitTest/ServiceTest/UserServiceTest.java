package com.flutt13.phonebook.UnitTest.ServiceTest;

import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.entities.dto.UserDto;
import com.flutt13.phonebook.exception.EntityExistsException;
import com.flutt13.phonebook.exception.EntityNotFoundException;
import com.flutt13.phonebook.exception.EntityNotValidException;
import com.flutt13.phonebook.repository.UserRepo;
import com.flutt13.phonebook.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepo userRepoMock;

    @Autowired
    private UserService userService;

    @Test
    public void testEmptyUserCreation() {
        UserDto dto = new UserDto();
        assertThrows(EntityNotValidException.class, () -> userService.addUser(dto));
    }

    @Test
    public void testDuplicateUserCreation() {
        UserDto dto = new UserDto("username", "user", "withname");
        when(userRepoMock.findByUserName("username")).thenReturn(new User());
        assertThrows(EntityExistsException.class, () -> userService.addUser(dto));
    }

    @Test
    public void testValidUserCreation() {
        UserDto dto = new UserDto("username", "user", "withname");
        Assertions.assertThat(userService.addUser(dto)).isExactlyInstanceOf(UserDto.class);
    }

    @Test
    public void testGetUser() {
        User user = new User(1L, "username", "user", "withname", "user withname");
        UserDto expected = new UserDto("username", "user", "withname");
        when(userRepoMock.findByUserName("username")).thenReturn(user);
        Assertions.assertThat(userService.getUser("username")).isEqualTo(expected);
    }

    @Test
    public void testSearchByNameFail() {
        when(userRepoMock.searchByName("name")).thenReturn(new ArrayList<User>());
        assertThrows(EntityNotFoundException.class, () -> userService.searchUsersByName("name"));
    }

    @Test
    public void testSearchByNameValid() {
        User user1 = new User();
        User user2 = new User();

        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        List<UserDto> dtos = new ArrayList<>(Arrays.asList(
                UserDto.UserToUserDto(user1),
                UserDto.UserToUserDto(user2)));

        when(userRepoMock.searchByName("name")).thenReturn(users);
        Assertions.assertThat(userService.searchUsersByName("name")).isEqualTo(dtos);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();

        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        List<UserDto> dtos = new ArrayList<>(Arrays.asList(
                UserDto.UserToUserDto(user1),
                UserDto.UserToUserDto(user2)));

        when(userRepoMock.findAll()).thenReturn(users);
        Assertions.assertThat(userService.getAllUsers()).isEqualTo(dtos);
    }

    @Test
    public void testEditUser() {
        User user = new User(1L, "username", "user", "withname", "user withname");
        UserDto updated = new UserDto(null, "newname", null);
        when(userRepoMock.findByUserName(user.getUserName())).thenReturn(user);
        Assertions.assertThat(userService.editUser(user.getUserName(), updated).getFirstName()).isEqualTo("newname");
    }


}
