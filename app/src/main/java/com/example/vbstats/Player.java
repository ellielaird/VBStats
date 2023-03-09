package com.example.vbstats;

import java.io.Serializable;

public class Player implements Serializable {


    private int playerNum;
    private  String name;
    private int swing;
    private int kill;
    private int error;
    private int assist;
    private int ace;
    private int SE;
    private int dig;
    private double countSR3;
    private double countSR2;
    private double countSR1;
    private double countSR0;
    boolean starter = false;


    public Player(int n){
        playerNum = n;
    }

    public int getPlayerNum(){return playerNum;}

    public Player(int n, String na, int s, int k, int e, int a, int ac, int serveError, int d, double serveReceive3, double serveReceive2, double serveReceive1, double serveReceive0, boolean starting){
        playerNum = n;
        name = na;
        swing = s;
        kill = k;
        error = e;
        assist = a;
        ace = ac;
        SE = serveError;
        dig = d;
        countSR3 = serveReceive3;
        countSR2 = serveReceive2;
        countSR1 = serveReceive1;
        countSR0 = serveReceive0;
        starter = starting;
    }

        public int getSwing(){return swing;}
        public int getKill(){return kill;}
        public int getError(){return error;}
        public int getAssist(){return assist;}
        public int getAce(){return ace;}
        public int getSE(){return SE;}
        public int getDig(){return dig;}
        public double getCountSR3(){return countSR3;}
        public double getCountSR2(){return countSR2;}
        public double getCountSR1(){return countSR1;}
        public double getCountSR0(){return countSR0;}
        public boolean getStarter(){return starter;}
        public  String getName(){return name;}


        public void addSwing() {swing++;}
        public void addKill(){kill++;}
        public void addError(){error++;}
        public void addAssist(){assist++;}
        public void addSe(){SE++;}
        public void addAce(){ace++;}
        public void addDig(){dig++;}
        public void addSR3(){countSR3++;}
        public void addSR2(){countSR2++;}
        public void addSR1(){countSR1++;}
        public void addSR0(){countSR0++;}

        public void setStarter(boolean starting){
        starter = starting;
        }
        public void setSwing(int s){swing = s;}
        public void setKill(int k){kill = k;}
        public void setError(int e){error = e;}
        public void setAssist(int a){assist = a;}
        public void setAce(int ac){ace = ac;}
        public void setSE(int serveError){SE = serveError;}
        public void setDig(int d){dig = d;}
        public void setCountSR3(double serveReceive3){countSR3 = serveReceive3;}
        public void setCountSR2(double serveReceive2){countSR2 = serveReceive2;}
        public void setCountSR1(double serveReceive1){countSR1 = serveReceive1;}
        public void setCountSR0(double serveReceive0){countSR0 = serveReceive0;}

    public Player(int n, String na){
        playerNum = n;
        name = na;
    }


        public void setName(String na){name = na;}

        public boolean equals(Object o)
        {
            if(o==null)
                return false;

            Player t = (Player) o;
            return ((Player) o).getPlayerNum() == this.playerNum;
        }

}
