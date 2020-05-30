package com.yucatancorp.examenandroid2020good.consumoWebService;

public class DatosDeEntrada {

    private int userId;
    private String env;
    private String os;

    public DatosDeEntrada(int userId, String env, String os) {
        this.userId = userId;
        this.env = env;
        this.os = os;
    }

    public int getUserId() {
        return userId;
    }

    public String getEnv() {
        return env;
    }

    public String getOs() {
        return os;
    }
}
