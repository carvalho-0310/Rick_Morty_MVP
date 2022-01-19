package com.example.rickmortymvp.interfaces;

import com.example.rickmortymvp.CharacterResponseVO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface CharacterSevice {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/";
    @GET("character/")
    Call<CharacterResponseVO> listCharacter( @Query("page") int currentPage );
}
