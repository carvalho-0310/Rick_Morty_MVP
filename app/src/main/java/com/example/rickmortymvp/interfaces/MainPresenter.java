package com.example.rickmortymvp.interfaces;

import com.example.rickmortymvp.Character;


public interface MainPresenter {

    public void onCreate();
    public void onClickCharacter(Character character);
    public void onClickTryAgain();
    public void onClickQuit();

}
