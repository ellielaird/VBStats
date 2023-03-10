package com.example.vbstats;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.gson;
//import com.google.gson.reflect.TypeToken;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Player> roster;
    ArrayList<Player> starting6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FIX - no need to store starting6 just complicates things...  can be generated from stored roster
        Gson gson = new Gson();
        String json = load("roster");
        //String json1 = load("starting6");
        Type playerType = new TypeToken<ArrayList<Player>>(){}.getType();
        roster = gson.fromJson(json, playerType);

        //FIX - need to deal with the very first time app is run and there is no roster saved...
        if(roster == null)
            // first time ever running app on this device
            roster = new ArrayList<>();

        // FIX - don't load starting 6, calculate it from roster
        //starting6 = gson.fromJson(json1, playerType);

        //FIX - added new method to return the starting6 list
        starting6 = getStarters(roster);
        ListContainer.setRoster(roster);
        ListContainer.setStarting6(starting6);



        TextView tvSetRosterButton = (TextView) findViewById(R.id.setRosterButton);
        tvSetRosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplication(), SetRosterActivity.class);
                startActivity(i1);
            }
        });

        TextView tvTakeStatsButton = (TextView) findViewById(R.id.takeStatsButton);
        tvTakeStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplication(), TakeStatsActivity.class);
                startActivity(i3);
            }
        });


    }

    // FIX - generates starting 6 list
    private ArrayList<Player> getStarters(ArrayList<Player> roster) {
        ArrayList<Player> starters = new ArrayList<>();

        for(Player p: roster)
            if(p.getStarter())
                starters.add(p);

        return starters;
    }

    @Override
protected void onResume() {
    super.onResume();


}


    @Override
    protected void onStop() {
    //THIS IS SAVING MY DATA!!!
        super.onStop();
        Gson gson = new Gson();
        String json = gson.toJson(roster);
       // String json1 = gson.toJson(starting6);
        save("roster", json);
        //save("starting6", json1);
    }

    public void save(String k, String v){

    // SAVE A SINGLE KEY VALUE PAIR TO SHARE PREFS
        SharedPreferences sh = getSharedPreferences("SharedPrefPractice", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putString(k, v);
        myEdit.apply();

    }

    public String load(String k){

        // SAVE A SINGLE KEY VALUE PAIR TO SHARE PREFS
        SharedPreferences sh = getSharedPreferences("SharedPrefPractice", MODE_PRIVATE);
        return sh.getString(k, "");
    }

}