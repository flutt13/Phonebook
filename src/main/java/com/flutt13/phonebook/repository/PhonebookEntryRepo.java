package com.flutt13.phonebook.repository;

import com.flutt13.phonebook.entities.PhonebookEntry;
import com.flutt13.phonebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhonebookEntryRepo extends JpaRepository<PhonebookEntry, Long> {
    public PhonebookEntry findByAlias(String alias);
    public void deleteByAlias(String alias);
    public void deleteByOwner(User owner);
    public List<PhonebookEntry> findByOwnerAndPhoneNumber(User owner, String phoneNumber);
    public List<PhonebookEntry> findByOwner(User owner);
}
