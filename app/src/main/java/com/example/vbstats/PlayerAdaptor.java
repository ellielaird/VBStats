package com.example.vbstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class PlayerAdaptor extends ArrayAdapter<Player>{

        public PlayerAdaptor(Context context, int id, ArrayList<Player> starting6){
            super (context, id, starting6);
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

          View currentItemView = convertView;

          if(currentItemView==null)
          {
              currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
          }

          Player currentPlayer = (Player) getItem(position);

          TextView tvPlayerNum = currentItemView.findViewById(R.id.playerNumber);
          tvPlayerNum.setText(Integer.toString(currentPlayer.getPlayerNum()));



        Spinner spinner1;
        ArrayList<String> subNames;

        ArrayList<Player> roster = ListContainer.getList();
        ArrayList<Player> starting6 = ListContainer.getStarting6();

        subNames = new ArrayList<String>();
        subNames.clear();
        subNames.add(0,"SUB");
        spinner1 = currentItemView.findViewById(R.id.subSpinner);

        for(int i = 0; i < roster.size(); i++){
            subNames.add(roster.get(i).getName());
        }

        ArrayAdapter adaptor1 = new ArrayAdapter<String>(currentItemView.getContext(), R.layout.support_simple_spinner_dropdown_item, subNames);
        spinner1.setAdapter(adaptor1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){

                if(i!=0) {
                    Player t = roster.get(i - 1);
                    t.setStarter(true);
                    Player c = currentPlayer;
                    c.setStarter(false);
                    starting6.add(starting6.indexOf(c), t);
                    starting6.remove(c);
                    adaptor1.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
                                           });


        Spinner spinner2;
        ArrayList<String> srRating;

        srRating = new ArrayList<String>();
        srRating.clear();
        srRating.add(0,"SR Rating");
        spinner2 = currentItemView.findViewById(R.id.srSpinner);
        srRating.add("0");
        srRating.add("1");
        srRating.add("2");
        srRating.add("3");

        ArrayAdapter adapter2 = new ArrayAdapter(currentItemView.getContext(),R.layout.support_simple_spinner_dropdown_item, srRating);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i!=0)
                {
                    String rating = srRating.get(i);
                    Player p = currentPlayer;
                    if(rating.equals("3")){p.addSR3();}
                    if(rating.equals("2")){p.addSR2();}
                    if(rating.equals("1")){p.addSR1();}
                    if(rating.equals("0")){p.addSR0();}

                    adapter2.notifyDataSetChanged();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Button swingButton = (Button)currentItemView.findViewById(R.id.swingButton);
        swingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 currentPlayer.addSwing();

            }
        });

        Button killButton = (Button)currentItemView.findViewById(R.id.killButton);
        killButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPlayer.addKill();

            }
        });

        Button assistButton = (Button)currentItemView.findViewById(R.id.assistButton);
        assistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPlayer.addAssist();

            }
        });



        Button errorButton = (Button)currentItemView.findViewById(R.id.errorButton);
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPlayer.addError();

            }
        });

        Button aceButton = (Button)currentItemView.findViewById(R.id.aceButton);
        aceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPlayer.addAce();

            }
        });

        Button seButton = (Button)currentItemView.findViewById(R.id.seButton);
        seButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPlayer.addSe();

            }
        });

        Button digButton = (Button)currentItemView.findViewById(R.id.digButton);
        digButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentPlayer.addDig();

            }
        });




        return currentItemView;

    }

}
