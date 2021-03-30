package com.flutt13.phonebook.UnitTest.EntityTest;

import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.entities.dto.UserDto;
import com.flutt13.phonebook.exception.EntityNotValidException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDtoTest {

    @Test
    public void testUserToUserDto() {
        User user = new User(1L, "username",
                "firstname", "lastname", "firstname lastname");
        UserDto dto = UserDto.UserToUserDto(user);
        UserDto expected = new UserDto("username", "firstname", "lastname");
        Assertions.assertThat(dto).isEqualTo(expected);
    }

    @Test
    public void testUserDtoToUser() {
        UserDto dto = new UserDto("username", "firstname", "lastname");
        User user = UserDto.UserDtoToUser(dto);
        User expected = new User(null, "username",
                "firstname", "lastname", "firstname lastname");
        Assertions.assertThat(user).isEqualTo(expected);
    }

    @Test
    public void testValidateUserDtoFail() {
        UserDto dto = new UserDto();
        assertThrows(EntityNotValidException.class, () -> UserDto.validateUserDto(dto));
    }
}
