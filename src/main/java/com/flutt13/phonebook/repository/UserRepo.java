package com.flutt13.phonebook.repository;

import com.flutt13.phonebook.entities.PhonebookEntry;
import com.flutt13.phonebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
    public void deleteByUserName(String userName);

    @Query(value = "select * from user u where u.full_name like %:name%", nativeQuery = true)
    public List<User> searchByName(@Param("name") String name);
}
