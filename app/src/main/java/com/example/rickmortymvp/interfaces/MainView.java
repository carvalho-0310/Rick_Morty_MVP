package com.example.rickmortymvp.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.rickmortymvp.Character;
import com.example.rickmortymvp.ListCharacterAdapter;


public interface MainView {
    public void showLoading();
    public void hideLoading();
    public void showCharacter(List<Character> listCharacter);
    public void startInfo(Character character);
    public void showModalError();



}
