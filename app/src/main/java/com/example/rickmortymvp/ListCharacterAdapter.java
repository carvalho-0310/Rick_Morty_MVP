package com.example.rickmortymvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickmortymvp.interfaces.OnClickCharacter;

import java.util.ArrayList;
import java.util.List;

public class ListCharacterAdapter extends RecyclerView.Adapter<ListCharacterAdapter.ViewHolder> {


    private List<Character> list = new ArrayList<>();
    private MainActivity mainActivity;

    public ListCharacterAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setListAdapter(List<Character> listCharacter) {
        list = listCharacter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_recyclerview, parent, false);
        return new ViewHolder(view, mainActivity);
    }


    @Override
    public void onBindViewHolder(@NonNull ListCharacterAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView nameView;
        private final TextView statusView;
        private Character character;

        public ViewHolder(@NonNull View itemView, OnClickCharacter clickCharacter) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            nameView = itemView.findViewById(R.id.name);
            statusView = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCharacter.onClickCharacter(character);

                }
            });
        }

        public void bind(Character character) {
            this.character = character;
            Glide.with(imageView)
                    .load(character.getImage())
                    .into(imageView);
            nameView.setText(character.getName());
            statusView.setText(character.getStatus());
            if (character.getStatus().equals("Dead")) {
                int color = ContextCompat.getColor(statusView.getContext(), R.color.colorRed);
                statusView.setTextColor(color);
            } else if (character.getStatus().equals("Alive")) {
                int color = ContextCompat.getColor(statusView.getContext(), R.color.colorgreem);
                statusView.setTextColor(color);
            } else {
                int color = ContextCompat.getColor(statusView.getContext(), R.color.colorBlck);
                statusView.setTextColor(color);
            }
        }
    }
}
