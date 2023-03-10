package com.example.vbstats;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;




public class TakeStatsActivity extends AppCompatActivity {

Button saveButton, readButton;
private String filename = "vbstats.csv";
private String filepath = "";
public static PlayerAdaptor playerAdaptor1;

File myExternalFile;

String myData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_stats);

    ArrayList<Player> roster = ListContainer.getList();
    ArrayList<Player> starting6 = ListContainer.getStarting6();

    saveButton = (Button)findViewById(R.id.takeStatsSaveButton);
    saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String allStats = "Player, Kills, Error, Attempts,,,Player,Assists,Digs,,Player,Aces,Errors,Rating,,Player,3,2,1,0,Rating\n";
            for(Player p: roster)
            {
                String name = p.getName();
                String kills = Integer.toString(p.getKill());
                String errors = Integer.toString(p.getError());
                String attempts = Integer.toString(p.getSwing());
                String assists = Integer.toString(p.getAssist());
                String digs = Integer.toString(p.getDig());
                String aces = Integer.toString(p.getAce());
                String SE = Integer.toString(p.getSE());
                String threes = Double.toString(p.getCountSR3());
                String twos = Double.toString(p.getCountSR2());
                String ones = Double.toString(p.getCountSR1());
                String zeros = Double.toString(p.getCountSR0());

                allStats += name + "," + kills + "," +errors +"," + attempts + ",,," + name +"," + assists + "," + digs + ",," + name +"," + aces +"," + SE + ",,," +  name +"," + threes +"," + twos +"," + ones +"," + zeros + ",,," +  "\n";
            }

            try{
                FileOutputStream fos = new FileOutputStream(myExternalFile);
                fos.write(allStats.getBytes());
                fos.close();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                for(Player p: roster){
                                    p.setKill(0);
                                    p.setError(0);
                                    p.setSwing(0);
                                    p.setAssist(0);
                                    p.setDig(0);
                                    p.setAce(0);
                                    p.setSE(0);
                                    p.setCountSR3(0.0);
                                    p.setCountSR2(0.0);
                                    p.setCountSR1(0.0);
                                    p.setCountSR0(0.0);
                                }

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Would you like to clear all stats?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }catch(IOException e) {
                e.printStackTrace();
            }

        }
    });

    if (!isExternalStorageAvailable() || isExternalStorageReadOnly()){
        saveButton.setEnabled(false);
    }
    else{
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
    }

        playerAdaptor1 = new PlayerAdaptor(this, R.layout.item, starting6);
        ListView listView = (ListView)findViewById(R.id.statsList);
        listView.setAdapter(playerAdaptor1);
        listView.setSmoothScrollbarEnabled(true);

        TextView tvTakeStatsBackButton = (TextView) findViewById(R.id.takeStatsBackButton);
        tvTakeStatsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
               // Intent i3 = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(i3);
            }
        });




    }



    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)){
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable(){
        String extStorageState = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(extStorageState))
        {
            return true;
        }
        return false;
    }
}