package com.flutt13.phonebook.UnitTest.EntityTest;

import com.flutt13.phonebook.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void testGenerateUserId() {
        User user = new User();
        user.setUserName(User.generateUserName());
        Assertions.assertThat(user.getUserName().length()).isEqualTo(8);
    }

    @Test
    public void testSetFullName() {
        User user = new User();
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setFullName();
        Assertions.assertThat(user.getFullName()).isEqualTo("firstname lastname");
    }
}
