package com.boxi.core.errors;

public enum ExceptionType {
    VALIDATION_EXCEPTION("validation.exception"),
    ENTITY_NOT_FOUND("not.found"),
    LOOKUP_NOT_FOUND("lookup.not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception"),
    VALUE_EXCEPTION("value.not.correct"),
    PARENT_NOT_FOUND("parent.not.found"),
    ACCESS_EXCEPTION("access.exception"),
    EXCEL_VALIDATION("excel.validation"),
    USER_DISABLED("user.disable"),
    COUNT_EXCEPTION("count.exception"),
    PASSWORD_REQUIRED("password.required"),
    ID_EMPTY("id.empty");


    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }

}
