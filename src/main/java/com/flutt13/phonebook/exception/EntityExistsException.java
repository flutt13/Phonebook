package com.flutt13.phonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="User with this username already exists")
public class EntityExistsException extends RuntimeException{
}
