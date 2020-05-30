package com.yucatancorp.examenandroid2020good.consumoWebService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface getDataFromWebService {

    @FormUrlEncoded
    @POST(".")
    Call<DatosDeSalida> enviarDatos(@Field("userId") int userId,
                                       @Field("env") String env,
                                       @Field("os") String os);
}
