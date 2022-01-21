package com.example.rickmortymvp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rickmortymvp.interfaces.MainView;
import com.example.rickmortymvp.interfaces.OnClickCharacter;


import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, OnClickCharacter {

    private ListCharacterAdapter characterListAdapter = new ListCharacterAdapter(this);
    private MainPresenterImpl mainPresenter = new MainPresenterImpl(this);
    private ProgressBar pbLoading;
    private RecyclerView rvCharacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCharacter = findViewById(R.id.rv_character);
        rvCharacter.setAdapter(characterListAdapter);
        rvCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                  mainPresenter.onClickTryAgain();
                }
            }
        });

        pbLoading = findViewById(R.id.main_pb_loading);

        mainPresenter.onCreate();


    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        rvCharacter.setVisibility(View.GONE);

    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
        rvCharacter.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCharacter(List<Character> listCharacter) {
        characterListAdapter.setListAdapter(listCharacter);
    }

    @Override
    public void startInfo(Character character) {
        Intent intent = new Intent(this, InfosActivity.class);
        intent.putExtra("c", character);
        startActivity(intent);
    }

    @Override
    public void showModalError() {
        new AlertDialog.Builder(this)
                .setTitle("Porra Morty")
                .setMessage("Você ta sem internet, não fode")
                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainPresenter.onClickTryAgain();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Porra Morty você fudeo comigo",Toast.LENGTH_SHORT).show();
                        mainPresenter.onClickQuit();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onClickCharacter(Character character) {
        mainPresenter.onClickCharacter(character);
    }
}