package com.qulix.serovdo.core.validator;

import com.qulix.serovdo.api.validator.DateValidator;

import java.sql.Date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class DateValidatorImpl implements DateValidator {
    private static final String REGEX_STRING = "^.{1,40}$";
    private static final String REGEX_DATE = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean isNumberValidator(Long id) {
        if ((id % 2 == 0) || (id % 2 == 1) || (id > 0)) {
            System.out.println(id % 2);
            System.out.println(IntStream.rangeClosed(2, Math.toIntExact(id / 2)).anyMatch(i -> id % i == 0));
            System.out.println(id.longValue());
            return true;
        }else {
            System.out.println(id % 2);
            return false;}
    }

    @Override
    public boolean isStringValidator(String string) {
        if (string.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_STRING);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean isDateFormatValidator(String date) {
        pattern = Pattern.compile(REGEX_DATE);
        matcher = pattern.matcher(date);
        return matcher.matches();
    }

    @Override
    public boolean isDateValidator(String startDate, String finishDate) {
        if (isDateFormatValidator(startDate) && isDateFormatValidator(finishDate)) {
            Date firstDate = Date.valueOf(startDate);
            Date secondDate = Date.valueOf(finishDate);
            return firstDate.before(secondDate);
        } else {
            return false;
        }
    }

}
