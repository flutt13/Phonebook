package com.flutt13.phonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="User not valid")
public class EntityNotValidException extends RuntimeException {
}
