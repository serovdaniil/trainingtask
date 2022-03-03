package com.qulix.serovdo.core.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorImplTest {
DateValidatorImpl validator=new DateValidatorImpl();
    @Test
    void isNumberValidator() {
assertTrue(validator.isNumberValidator((long)5.5));
    }

    @Test
    void isStringValidator() {
        assertTrue(validator.isStringValidator("asdasdf"));
        assertFalse(validator.isStringValidator(""));
        assertFalse(validator.isStringValidator("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void isDateValidator() {
        assertFalse(validator.isDateValidator("200-12-85","1-11-11"));
        assertTrue(validator.isDateValidator("0001-12-31","2022-11-11"));
        assertFalse(validator.isDateValidator("2002-12-85","2021-11-11"));
        assertTrue(validator.isDateValidator("2002-06-05","2003-11-07"));
        assertFalse(validator.isDateValidator("2022-07-08","2021-11-11"));
    }
}