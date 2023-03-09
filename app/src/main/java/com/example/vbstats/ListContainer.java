package com.example.vbstats;

import java.util.ArrayList;

public class ListContainer {

    private static ArrayList<Player> roster;

    public static ArrayList<Player> getList(){
        if(roster == null)
            roster = new ArrayList<>();

        return roster;

    }

    private static ArrayList<Player> starting6;

    public static ArrayList<Player> getStarting6(){

        if(starting6 == null)

            starting6 = new ArrayList<>();

        return starting6;

    }

    public static void setRoster(ArrayList<Player> r){roster = r;}

    public static void setStarting6(ArrayList<Player> r){starting6 = r;}

    public static void save(){

    }

    public static void load(){

    }
}
