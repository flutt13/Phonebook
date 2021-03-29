package com.flutt13.phonebook.entities.dto;

import com.flutt13.phonebook.entities.User;
import lombok.Data;

@Data
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
        return user;
    }

    public static User UpdateUserFromDto(User user, UserDto dto) {
        if (dto.userName != null) user.setUserName(dto.userName);
        if (dto.firstName != null) user.setFirstName(dto.firstName);
        if (dto.lastName != null) user.setLastName(dto.lastName);
        return user;
    }
}
