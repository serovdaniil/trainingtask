package com.qulix.serovdo.api.validator;

import java.sql.Date;

public interface DateValidator {
    boolean isNumberValidator(Long id);

    boolean isStringValidator(String string);

    boolean isDateValidator(String startDate, String finishDate);
}
