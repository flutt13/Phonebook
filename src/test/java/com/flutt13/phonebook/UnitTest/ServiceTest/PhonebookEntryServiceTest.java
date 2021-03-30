package com.flutt13.phonebook.UnitTest.ServiceTest;

import com.flutt13.phonebook.entities.PhonebookEntry;
import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.entities.dto.PhonebookEntryDto;
import com.flutt13.phonebook.exception.EntityExistsException;
import com.flutt13.phonebook.exception.EntityNotValidException;
import com.flutt13.phonebook.repository.PhonebookEntryRepo;
import com.flutt13.phonebook.repository.UserRepo;
import com.flutt13.phonebook.service.PhonebookEntryService;
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
public class PhonebookEntryServiceTest {

    @MockBean
    private PhonebookEntryRepo phonebookEntryRepoMock;

    @MockBean
    private UserRepo userRepoMock;

    @Autowired
    private PhonebookEntryService phonebookEntryService;

    @Test
    public void testValidPhonebookEntryCreation() {
        PhonebookEntryDto entryDto = new PhonebookEntryDto("alias", "firstname",
                "lastname", "+79284762312");
        when(userRepoMock.findByUserName("username")).thenReturn(new User());
        Assertions.assertThat(phonebookEntryService.createPhonebookEntry("username", entryDto))
                .isExactlyInstanceOf(PhonebookEntryDto.class);
    }

    @Test
    public void testInvalidPhonebookEntryCreation() {
        PhonebookEntryDto entryDto = new PhonebookEntryDto();
        when(userRepoMock.findByUserName("username")).thenReturn(new User());
        assertThrows(EntityNotValidException.class,
                () -> phonebookEntryService.createPhonebookEntry("username", entryDto));
    }

    @Test
    public void testPhonebookEntryCreationWithoutAlias() {
        PhonebookEntryDto entryDto = new PhonebookEntryDto(null, "+79284762312",
                "firstname", "lastname");
        when(userRepoMock.findByUserName("username")).thenReturn(new User());
        Assertions.assertThat(phonebookEntryService.createPhonebookEntry("username", entryDto)
                .getAlias()).isNotEmpty();
    }

    @Test
    public void testDuplicatePhonebookEntryCreation() {
        PhonebookEntryDto entryDto = new PhonebookEntryDto("alias", "+79284762312",
                "firstname", "lastname");
        when(userRepoMock.findByUserName("username")).thenReturn(new User());
        when(phonebookEntryRepoMock.findByAlias(entryDto.getAlias())).thenReturn(new PhonebookEntry());
        assertThrows(EntityExistsException.class,
                () -> phonebookEntryService.createPhonebookEntry("username", entryDto));
    }

    @Test
    public void testGetPhonebookEntry() {
        User owner = new User();
        PhonebookEntry entry = new PhonebookEntry(1L, "alias", "+79284762312",
                "firstname", "lastname", "firstname lastname", owner);
        when(phonebookEntryRepoMock.findByAlias(entry.getAlias())).thenReturn(entry);
        Assertions.assertThat(phonebookEntryService.getPhonebookEntry(entry.getAlias()))
                .isEqualTo(PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry));
    }

    @Test
    public void testGetOwnerPhonebookEntry() {
        User owner1 = new User();
        User owner2 = new User();

        owner1.setUserName("owner1");
        owner2.setUserName("owner2");

        List<PhonebookEntry> owner1Entries = new ArrayList<>(Arrays.asList(new PhonebookEntry(), new PhonebookEntry()));
        owner1Entries.get(0).setOwner(owner1);
        owner1Entries.get(1).setOwner(owner1);

        List<PhonebookEntry> owner2Entries = new ArrayList<>(Arrays.asList(new PhonebookEntry()));
        owner2Entries.get(0).setOwner(owner2);

        List<PhonebookEntryDto> owner1EntriesDto = new ArrayList<>();
        for (PhonebookEntry entry : owner1Entries) {
            owner1EntriesDto.add(PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry));
        }

        when(userRepoMock.findByUserName("owner1")).thenReturn(owner1);
        when(phonebookEntryRepoMock.findByOwner(owner1)).thenReturn(owner1Entries);
        Assertions.assertThat(phonebookEntryService.getOwnerPhonebookEntries(owner1.getUserName()))
                .isEqualTo(owner1EntriesDto);
    }

    @Test
    public void testEditPhonebookEntry() {
        User owner = new User(1L, "username", "user",
                "withname", "user withname");
        PhonebookEntry entry = new PhonebookEntry(1L, "alias", "+79284762312",
                "firstname", "lastname", "firstname lastname", owner);
        PhonebookEntryDto updated = new PhonebookEntryDto(null, "+74782910234",
                null, null);
        when(userRepoMock.findByUserName(owner.getUserName())).thenReturn(owner);
        when(phonebookEntryRepoMock.findByAlias(entry.getAlias())).thenReturn(entry);
        Assertions.assertThat(phonebookEntryService.editPhonebookEntry(entry.getAlias(), updated).getPhoneNumber())
                .isEqualTo(updated.getPhoneNumber());
    }
}
