package com.chseidler.invoicesappbe.utils;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELDS("Favor preencher todos campos."),
    RECORD_ALREADY_EXIST("Usuário ja existente."),
    RECORD_NOT_FOUND("Usuário não encontrado.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
