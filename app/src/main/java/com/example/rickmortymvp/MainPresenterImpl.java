package com.example.rickmortymvp;

import android.util.Log;

import com.example.rickmortymvp.interfaces.CharacterSevice;
import com.example.rickmortymvp.interfaces.MainPresenter;
import com.example.rickmortymvp.interfaces.MainView;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenterImpl implements MainPresenter {
    private int page = 1;

    private Retrofit rf = new Retrofit.
            Builder().
            baseUrl(CharacterSevice.BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build();
    private CharacterSevice sevice = rf.create(CharacterSevice.class);

    private MainView mainView;

    public MainPresenterImpl(MainView mainActivity) {
        this.mainView = mainActivity;
    }

    @Override
    public void onCreate() {
        requetCharacterList();
    }

    @Override
    public void onClickCharacter(Character character) {
        mainView.startInfo(character);

    }

    @Override
    public void onClickTryAgain() {
        requetCharacterList();
    }

    @Override
    public void onClickQuit() {
        mainView.finish();
    }

    private void requetCharacterList() {

        mainView.showLoading();
        final String TAG = "service";

        sevice.listCharacter(page)
                .enqueue(
                        new Callback<CharacterResponseVO>() {
                            @Override
                            public void onResponse(Call<CharacterResponseVO> call, Response<CharacterResponseVO> response) {
                                if (!response.isSuccessful()) {
                                    Log.i(TAG, "Erro: " + response.code());
                                    mainView.showModalError();
                                } else {
                                    CharacterResponseVO characterResponse = response.body();
                                    mainView.showCharacter(characterResponse.getResults());
                                    mainView.hideLoading();
                                }
                            }
                            @Override
                            public void onFailure(Call<CharacterResponseVO> call, Throwable t) {
                                Log.e(TAG, "Erro: " + t.getMessage());
                                mainView.showModalError();
                            }
                        });
        if (page<43) {
            page++;
        }
    }
}


