package com.flutt13.phonebook.entities.dto;

import com.flutt13.phonebook.entities.PhonebookEntry;
import lombok.Data;

@Data
public class PhonebookEntryDto {

    private String alias;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    public static PhonebookEntryDto PhonebookEntrytoPhonebookEntryDto(PhonebookEntry entry) {
        PhonebookEntryDto dto = new PhonebookEntryDto();
        dto.alias = entry.getAlias();
        dto.phoneNumber = entry.getPhoneNumber();
        dto.firstName = entry.getFirstName();
        dto.lastName = entry.getLastName();
        return dto;
    }

    public static PhonebookEntry PhonebookEntryDtoToPhonebookEntry(PhonebookEntryDto dto) {
        PhonebookEntry entry = new PhonebookEntry();
        entry.setAlias(dto.alias);
        entry.setPhoneNumber(dto.phoneNumber);
        entry.setFirstName(dto.firstName);
        entry.setLastName(dto.lastName);
        return entry;
    }

    public static PhonebookEntry UpdatePhonebookEntryFromDto(PhonebookEntry entry, PhonebookEntryDto dto) {
        if (dto.alias != null) entry.setAlias(dto.alias);
        if (dto.phoneNumber != null) entry.setPhoneNumber(dto.phoneNumber);
        if (dto.firstName != null) entry.setFirstName(dto.firstName);
        if (dto.lastName != null) entry.setLastName(dto.lastName);
        return entry;
    }
}
