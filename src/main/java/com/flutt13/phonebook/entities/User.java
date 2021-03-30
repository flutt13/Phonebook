package com.flutt13.phonebook.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true)
    @Size(max = 32)
    @NotNull
    private String userName;

    @Size(max = 256)
    @Column
    @NotNull
    private String firstName;

    @Size(max = 256)
    @Column
    @NotNull
    private String lastName;

    @Size(max = 512)
    @Column
    @NotNull
    private String fullName;

    public static String generateUserName() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public void setFullName() {
        this.fullName = firstName + " " + lastName;
    }

}
