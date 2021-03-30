package com.flutt13.phonebook.UnitTest.DtoTest;

import com.flutt13.phonebook.entities.PhonebookEntry;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonebookEntryTest {

    @Test
    public void testGenerateAlias() {
        PhonebookEntry entry = new PhonebookEntry();
        entry.setFirstName("firstname");
        entry.setLastName("lastname");
        entry.setAlias(PhonebookEntry.generateAlias(entry));
        Assertions.assertThat(entry.getAlias()).isEqualTo("FL");
    }

    @Test
    public void testSetFullName() {
        PhonebookEntry entry = new PhonebookEntry();
        entry.setFirstName("firstname");
        entry.setLastName("lastname");
        entry.setFullName();
        Assertions.assertThat(entry.getFullName()).isEqualTo("firstname lastname");
    }
}
