package com.yucatancorp.examenandroid2020good.consumoWebService;

public class DatosDeSalida {

    private String code;
    private String message;
    private String response;
    private boolean success;

    public DatosDeSalida(String code, String message, String response, boolean success) {
        this.code = code;
        this.message = message;
        this.response = response;
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getResponse() {
        return response;
    }

    public boolean isSuccess() {
        return success;
    }
}
