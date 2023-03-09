package com.example.vbstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class Player1Adaptor extends ArrayAdapter<Player> {

    public Player1Adaptor(Context context, int id, ArrayList<Player> roster) {
        super(context, id, roster);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.currentrosteritem, parent, false);
        }

        Player currentPlayer = (Player) getItem(position);

        TextView tvPlayerName = currentItemView.findViewById(R.id.currentRosterName);
        tvPlayerName.setText(currentPlayer.getName());

        TextView tvPlayerNum = currentItemView.findViewById(R.id.currentRosterNumber);
        tvPlayerNum.setText(Integer.toString(currentPlayer.getPlayerNum()));

        return currentItemView;
    }

}