package com.example.rickmortymvp.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.rickmortymvp.Character;
import com.example.rickmortymvp.ListCharacterAdapter;


public interface MainView {
     void showLoading();
     void hideLoading();
     void showCharacter(List<Character> listCharacter);
     void startInfo(Character character);
     void showModalError();
     void finish();



}
