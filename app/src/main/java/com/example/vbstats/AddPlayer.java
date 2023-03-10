package com.example.vbstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        ArrayList<Player> roster = ListContainer.getList();
        ArrayList<Player> starting6 = ListContainer.getStarting6();

        Button addPlayer = (Button)findViewById(R.id.finalAddPlayer);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addName = (EditText) findViewById(R.id.addPlayerName);
                String playerName = addName.getText().toString();

                EditText addNumber = (EditText) findViewById(R.id.addPlayerNumber);
                String StringPlayerNum = addNumber.getText().toString();
                int playerNum = Integer.parseInt(StringPlayerNum);

                CheckBox checkbox = (CheckBox)findViewById(R.id.starterCheckBox);
                    if(checkbox.isChecked()==true){
                        roster.add(new Player(playerNum, playerName, 0,0, 0, 0, 0, 0, 0, 0.0,0.0,0.0,0.0, true));
                        starting6.add(roster.get(roster.size()-1));
                    }
                    else
                        roster.add(new Player(playerNum, playerName, 0, 0, 0, 0, 0, 0, 0, 0.0,0.0,0.0,0.0,false));
                finish();
            }
        });

        TextView tvAddPlayerBackButton = (TextView) findViewById(R.id.addPlayerBackButton);
        tvAddPlayerBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
                }

        });
    }
}