package com.example.rickmortymvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class InfosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        Character character = (Character) getIntent().getSerializableExtra("c");
        Toast.makeText(this, character.getName(), Toast.LENGTH_LONG).show();
        ImageView imageView = findViewById(R.id.image_info);
        TextView nameView = findViewById(R.id.name_info);
        TextView statusView = findViewById(R.id.status_info);
        TextView speciesView = findViewById(R.id.species_info);
        TextView genderView = findViewById(R.id.gender_info);
        TextView originView = findViewById(R.id.origin_info);
        TextView localizacaoView = findViewById(R.id.localizacao_info);

        Glide.with(imageView)
                .load(character.getImage())
                .into(imageView);
        nameView.setText(character.getName());
        statusView.setText(character.getStatus());
        speciesView.setText(character.getSpecies());
        genderView.setText(character.getGender());
        originView.setText(character.getOrigin().getName());
        localizacaoView.setText(character.getLocation().getName());
        if (character.getStatus().equals("Dead")) {
            int color = ContextCompat.getColor(statusView.getContext(), R.color.colorRedTrans);
            statusView.setBackgroundColor(color);
        } else if (character.getStatus().equals("Alive")) {
            int color = ContextCompat.getColor(statusView.getContext(), R.color.colorgreemTrans);
            statusView.setBackgroundColor(color);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
