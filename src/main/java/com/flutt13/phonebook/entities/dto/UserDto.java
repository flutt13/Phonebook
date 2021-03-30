package com.flutt13.phonebook.entities.dto;

import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.exception.EntityNotValidException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;

    private String firstName;

    private String lastName;

    public static UserDto UserToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.userName = user.getUserName();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        return dto;
    }

    public static User UserDtoToUser(UserDto dto) {
        User user = new User();
        user.setUserName(dto.userName);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setFullName();
        return user;
    }

    public static User UpdateUserFromDto(User user, UserDto dto) {
        if (dto.userName != null) user.setUserName(dto.userName);
        if (dto.firstName != null) user.setFirstName(dto.firstName);
        if (dto.lastName != null) user.setLastName(dto.lastName);
        user.setFullName();
        return user;
    }

    public static void validateUserDto(UserDto dto) {
        if (dto.firstName == null) throw new EntityNotValidException();
        if (dto.lastName == null) throw new EntityNotValidException();
    }
}
