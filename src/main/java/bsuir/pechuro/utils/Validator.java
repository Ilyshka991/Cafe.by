package bsuir.pechuro.utils;

import bsuir.pechuro.exception.validation.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {

    private static final String REGEX_FOR_NAME = "([A-Z][a-z]+)|([А-Я][а-я]+)";
    private static final String REGEX_FOR_PRODUCT_NAME = "([A-Z][a-z\\s\\-\\`A-Z]+)|([А-Я][а-я\\s\\-\\`А-Я]+)";
    private static final String REGEX_FOR_LOGIN = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$";
    private static final String REGEX_FOR_PASSWORD = "\\w{6,}";
    private static final String REGEX_FOR_EMAIL = "[0-9a-z\\_\\-\\.]+@[0-9a-z_-]+\\.[a-z]{2,5}";

    private static Pattern pattern;
    private static Matcher matcher;


    public final static void isNull(Object... objects) throws ValidatorException {
        for (Object ob : objects) {
            if (ob == null) {
                throw new ValidatorException("input error (string is null)");
            }
        }
    }


    public final static void isEmptyString(String... strings) throws ValidatorException {
        for (String s : strings) {
            if (s.isEmpty()) {
                throw new ValidatorException("input error (string is empty)");
            }
        }
    }


    public final static void matchProperName(String... strings) throws ValidatorException {
        pattern = Pattern.compile(REGEX_FOR_NAME);
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("name format error");
            }
        }
    }


    public final static void matchEmail(String... strings) throws ValidatorException {
        pattern = Pattern.compile(REGEX_FOR_EMAIL);
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("e-mail format error");
            }
        }
    }


    public final static void matchLogin(String... strings) throws ValidatorException {
        pattern = Pattern.compile(REGEX_FOR_LOGIN);
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("login format error");
            }
        }
    }


    public final static void matchPassword(String... strings) throws ValidatorException {
        pattern = Pattern.compile(REGEX_FOR_PASSWORD);
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("password format error");
            }
        }
    }


    public final static void matchProductName(String... strings) throws ValidatorException {
        pattern = Pattern.compile(REGEX_FOR_PRODUCT_NAME);
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("product name format error");
            }
        }
    }
}
