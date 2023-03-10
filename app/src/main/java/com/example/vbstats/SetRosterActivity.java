package com.example.vbstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SetRosterActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> names;
    //FIX
    ArrayAdapter adaptor;
    Player1Adaptor playerAdaptor2;
    ArrayList<Player> roster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_roster);

        //FIX
        roster = ListContainer.getList();
        ArrayList<Player> starting6 = ListContainer.getStarting6();

        // FIX - extracted code to method to initilize spinner adapter bedcause need
        //      to call from onResume as well.
        populateSpinnerNames();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i!=0 && roster.get(i-1).getStarter()==true) {
                   Player t = roster.get(i-1);

                    // remove from starting 6
                    //had to implement equal method on the player object to make this work//
                    boolean b = starting6.remove(t);

                    // remove from roster
                    roster.remove(i-1);
                    adaptor.notifyDataSetChanged();
                }

                else if(i != 0) {
                    roster.remove(i-1);
                    adaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        playerAdaptor2 = new Player1Adaptor(this, R.layout.currentrosteritem, roster);
        ListView listView = (ListView)findViewById(R.id.rosterList);
        listView.setAdapter(playerAdaptor2);


        TextView tvBackButton = (TextView) findViewById(R.id.backButton);
        tvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tvAddPlayerButton = (TextView) findViewById(R.id.addPlayerButton);
        tvAddPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(getApplicationContext(), AddPlayer.class);
                startActivity(i5);
            }
        });
    }

    //FIX - moved from onCreate to a method so can use in onResume as well...
    private void populateSpinnerNames() {
        spinner = findViewById(R.id.removePlayerSpinner);

        names = new ArrayList<String>();
        names.clear();
        names.add(0,"Select player to delete...");

        for(int i = 0; i < roster.size(); i++)
        {
            names.add(roster.get(i).getName());
        }
        adaptor = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, names);
        spinner.setAdapter(adaptor);
    }

    // FIX - need to have this so the list updates when players are changed
    @Override
    protected void onResume() {
        //FIX
        super.onResume();
        playerAdaptor2.notifyDataSetChanged();
        populateSpinnerNames();
    }
}