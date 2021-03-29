package com.flutt13.phonebook.entities;

import com.flutt13.phonebook.entities.dto.PhonebookEntryDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "phonebook")
public class PhonebookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long entryId;

    @Column(unique = true)
    @Size(max = 32)
    @NotNull
    private String alias;

    @Column
    @Size(max = 256)
    @NotNull
    private String phoneNumber;

    @Column
    @Size(max = 256)
    @NotNull
    private String firstName;

    @Column
    @Size(max = 256)
    @NotNull
    private String lastName;

    @Column
    @Size(max = 512)
    @NotNull
    private String fullName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    @NotNull
    private User owner;

    public static String generateAlias(PhonebookEntry entry) {
        StringBuilder alias = new StringBuilder();
        alias.append(entry.getFirstName().toUpperCase().charAt(0));
        alias.append(entry.getLastName().toUpperCase().charAt(0));
        return alias.toString();
    }

    public void setFullName() {
        this.fullName = firstName + " " + lastName;
    }

}
