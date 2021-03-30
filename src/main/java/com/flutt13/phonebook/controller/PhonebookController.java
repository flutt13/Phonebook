package com.flutt13.phonebook.controller;

import com.flutt13.phonebook.entities.dto.PhonebookEntryDto;
import com.flutt13.phonebook.service.PhonebookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user/{userName}/phonebook")
public class PhonebookController {

    @Autowired
    PhonebookEntryService phonebookEntryService;

    @PostMapping
    @ResponseBody
    public PhonebookEntryDto createPhonebookEntry(
            @PathVariable("userName") String userName,
            PhonebookEntryDto phonebookEntryDto) {
        return phonebookEntryService.createPhonebookEntry(userName, phonebookEntryDto);
    }

    @GetMapping
    @ResponseBody
    public List<PhonebookEntryDto> getPhonebookEntriesByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        return phonebookEntryService.getPhonebookEntriesByPhoneNumber(phoneNumber);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<PhonebookEntryDto> getOwnerPhonebookEntries(@PathVariable("userName") String userName) {
        return phonebookEntryService.getOwnerPhonebookEntries(userName);
    }

    @GetMapping("{alias}")
    @ResponseBody
    public PhonebookEntryDto getPhonebookEntry(@PathVariable("alias") String alias) {
        return phonebookEntryService.getPhonebookEntry(alias);
    }

    @DeleteMapping("{alias}")
    @ResponseBody
    public void deletePhonebookEntry(@PathVariable("alias") String alias) {
        phonebookEntryService.deletePhonebookEntry(alias);
    }

    @PatchMapping("{alias}")
    @ResponseBody
    public PhonebookEntryDto editPhonebookEntry(
            @PathVariable("alias") String alias,
            PhonebookEntryDto phonebookEntryDto) {
        return phonebookEntryService.editPhonebookEntry(alias, phonebookEntryDto);
    }
}
