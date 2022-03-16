package com.chseidler.invoicesappbe.utils;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELDS("Favor preencher todos campos."),
    RECORD_ALREADY_EXIST("Registro ja existente."),
    RECORD_NOT_FOUND("Registro não encontrado."),
    CAN_NOT_LOGIN("Usuário e(ou) senha incorreto(s).");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
