package com.example.validationdemo.validation;

public class ValidationUtils {

    public static final String NAME_PATTERN = "[A-Z][a-z]+";

    // https://regex101.com/library/3uvtNl
    public static final String EMAIL_PATTERN = "^((?:[A-Za-z0-9!#$%&'*+\\-\\/=?^_`{|}~]|(?<=^|\\.)\"|\"(?=$|\\.|@)|(?<=\".*)[ .](?=.*\")|(?<!\\.)\\.){1,64})(@)((?:[A-Za-z0-9.\\-])*(?:[A-Za-z0-9])\\.(?:[A-Za-z0-9]){2,})$";
}
