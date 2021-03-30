package com.flutt13.phonebook.UnitTest.DtoTest;

import com.flutt13.phonebook.entities.PhonebookEntry;
import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.entities.dto.PhonebookEntryDto;
import com.flutt13.phonebook.exception.EntityNotValidException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonebookEntryDtoTest {

    @Test
    public void testPhonebookEntrytoPhonebookEntryDto() {
        User owner = new User();
        PhonebookEntry entry = new PhonebookEntry(
                1L, "alias", "+74905837651",
                "firstname", "lastname", "firstname lastname", owner);
        PhonebookEntryDto dto = PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry);
        PhonebookEntryDto expected = new PhonebookEntryDto("alias", "+74905837651",
                "firstname", "lastname");
        Assertions.assertThat(dto).isEqualTo(expected);
    }

    @Test
    public void testPhonebookEntryDtoToPhonebookEntry() {
        PhonebookEntryDto dto = new PhonebookEntryDto("alias", "+74905837651",
                "firstname", "lastname");
        PhonebookEntry entry = PhonebookEntryDto.PhonebookEntryDtoToPhonebookEntry(dto);
        PhonebookEntry expected = new PhonebookEntry(null, "alias", "+74905837651",
                "firstname", "lastname", "firstname lastname", null);
        Assertions.assertThat(entry).isEqualTo(expected);
    }

    @Test
    public void testValidatePhonebookEntryDtoFail() {
        PhonebookEntryDto dto = new PhonebookEntryDto();
        assertThrows(EntityNotValidException.class, () -> PhonebookEntryDto.validatePhonebookEntryDto(dto));
    }

}
