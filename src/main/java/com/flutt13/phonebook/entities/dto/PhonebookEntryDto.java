package com.flutt13.phonebook.entities.dto;

import com.flutt13.phonebook.entities.PhonebookEntry;
import com.flutt13.phonebook.exception.EntityNotValidException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        entry.setFullName();
        return entry;
    }

    public static PhonebookEntry UpdatePhonebookEntryFromDto(PhonebookEntry entry, PhonebookEntryDto dto) {
        if (dto.alias != null) entry.setAlias(dto.alias);
        if (dto.phoneNumber != null) entry.setPhoneNumber(dto.phoneNumber);
        if (dto.firstName != null) entry.setFirstName(dto.firstName);
        if (dto.lastName != null) entry.setLastName(dto.lastName);
        entry.setFullName();
        return entry;
    }

    public static void validatePhonebookEntryDto(PhonebookEntryDto dto) {
        if (dto.phoneNumber == null) throw new EntityNotValidException();
        if (dto.firstName == null) throw new EntityNotValidException();
        if (dto.lastName == null) throw new EntityNotValidException();
    }
}
