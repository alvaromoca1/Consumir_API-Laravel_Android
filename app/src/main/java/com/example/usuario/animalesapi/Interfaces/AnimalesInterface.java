package com.example.usuario.animalesapi.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimalesInterface {

    @GET("animales")
    Call<String> getString();
}
