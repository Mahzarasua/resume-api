package com.mahzarasua.resumeapi.validator;

public interface Validator<E> {
    void validate(E object);
}
