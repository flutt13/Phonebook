package com.flutt13.phonebook.service;

import com.flutt13.phonebook.entities.PhonebookEntry;
import com.flutt13.phonebook.entities.User;
import com.flutt13.phonebook.entities.dto.PhonebookEntryDto;
import com.flutt13.phonebook.exception.EntityExistsException;
import com.flutt13.phonebook.exception.EntityNotFoundException;
import com.flutt13.phonebook.exception.EntityNotValidException;
import com.flutt13.phonebook.repository.PhonebookEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhonebookEntryService {

    @Autowired
    PhonebookEntryRepo phonebookEntryRepo;

    @Autowired
    UserService userService;

    private PhonebookEntry findPhonebookEntry(String alias) {
        PhonebookEntry entry = phonebookEntryRepo.findByAlias(alias);
        if (entry == null) throw new EntityNotFoundException();
        return entry;
    }

    public PhonebookEntryDto createPhonebookEntry(String userName, PhonebookEntryDto phonebookEntryDto) {
        PhonebookEntryDto.validatePhonebookEntryDto(phonebookEntryDto);
        PhonebookEntry entry = PhonebookEntryDto.PhonebookEntryDtoToPhonebookEntry(phonebookEntryDto);
        User owner = userService.findUser(userName);
        entry.setOwner(owner);
        if (entry.getAlias() == null) entry.setAlias(PhonebookEntry.generateAlias(entry));
        if (phonebookEntryRepo.findByAlias(entry.getAlias()) != null) throw new EntityExistsException();
        try {
            phonebookEntryRepo.save(entry);
        } catch (DataIntegrityViolationException e) {
            throw new EntityNotValidException();
        }
        return PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry);
    }

    public PhonebookEntryDto getPhonebookEntry(String alias) {
        PhonebookEntry entry = findPhonebookEntry(alias);
        return PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry);
    }

    public List<PhonebookEntryDto> getOwnerPhonebookEntries(String userName) {
        User owner = userService.findUser(userName);
        List<PhonebookEntry> entries = phonebookEntryRepo.findByOwner(owner);
        if (entries.isEmpty()) throw new EntityNotFoundException();
        List<PhonebookEntryDto> entriesDto = new ArrayList<>();
        for (PhonebookEntry entry : entries) {
            entriesDto.add(PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry));
        }
        return entriesDto;
    }

    @Transactional
    public void deletePhonebookEntry(String alias) {
        PhonebookEntry entry = findPhonebookEntry(alias);
        entry.setOwner(null);
        phonebookEntryRepo.deleteByAlias(alias);
    }

    public void deleteUserEntries(User user) {
        phonebookEntryRepo.deleteByOwner(user);
    }

    public PhonebookEntryDto editPhonebookEntry(String alias, PhonebookEntryDto phonebookEntryDto) {
        PhonebookEntry entry = findPhonebookEntry(alias);
        PhonebookEntry updated = PhonebookEntryDto.UpdatePhonebookEntryFromDto(entry, phonebookEntryDto);
        phonebookEntryRepo.save(updated);
        return PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(updated);
    }

    public List<PhonebookEntryDto> getPhonebookEntriesByPhoneNumber(String phoneNumber) {
        List<PhonebookEntry> entries = phonebookEntryRepo.findByPhoneNumber(phoneNumber);
        if (entries.isEmpty()) throw new EntityNotFoundException();
        List<PhonebookEntryDto> entriesDto = new ArrayList<>();
        for (PhonebookEntry entry : entries) {
            entriesDto.add(PhonebookEntryDto.PhonebookEntrytoPhonebookEntryDto(entry));
        }
        return entriesDto;
    }

}
