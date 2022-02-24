package com.qulix.serovdo.api.validator;

public interface DateValidator {
    boolean isNumberValidator(Long id);

    boolean isStringValidator(String string);

    boolean isDateValidator(String startDate, String finishDate);
}
