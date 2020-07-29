package com.mygdx.game;
import java.util.Random;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;

public class Utils{
    ArrayList<String> strings = new ArrayList();
    Random rand = new Random();
    Utils(){
        strings.add("Mission: Defeat Corona");
        strings.add("A - Attack : D - Defend");
        strings.add("   Attack Successfull");
        strings.add("   Critical Attack!");
        strings.add("     Attack Fail");
        strings.add("   Defence Successfull");
        strings.add("   Defence Perfectly");
        strings.add("      Defence Fail");
        strings.add("Corona Critical Attack!");
        strings.add("  Corona Defence Fail");
        strings.add("Corona Defence Success");
        strings.add("Corona Defend Perfectly");
        strings.add("Corona Attack Successfull");
        strings.add("Corona Attack Fail");
    }
    public int random(int randint){
        return rand.nextInt(randint);
    }
    public String getString(int indice){
        for(int i = 0; i < strings.size(); i++){
            if(i == indice)
                return strings.get(i);
        }
        return null;
    }
    public int nextString(int idx, int coronaDamage, int coronaHit, int coronaDefence){
        //System.out.println(coronaHit);
        return 1;
    }

    public int nextStringGlobuloAttack(int globuloDamage, int globuloHit){
        if(globuloHit < 30)
            return 4;
        else if(globuloDamage > 90)
            return 3;
        else
            return 2;
    }
    public int nextStringGlobuloDefense(int globuloDefence){
        if(globuloDefence < 30)
            return strings.size() - 7;
        else if(globuloDefence < 90)
            return strings.size() - 9;
        else
            return strings.size() - 8; 
    }
    public int coronaStringAttack(int hit, int damage){
        if(hit < 30){
            return strings.size()-1;
        }
        else if(damage >= 90){
            return strings.size()-6;
        }
        
        return strings.size()-2;
    }
    public int coronaDefense(int coronaDefence){
        if(coronaDefence < 30){     //defence fail
            return strings.size()-5;
        }
        else if(coronaDefence >= 90){//critical
            return strings.size()-3;
        }
        else{                       //normal
            return strings.size()-4;
        }
    }

    public boolean permitComment(int commentID){
        if(commentID != 1)
            return true;
        return false;
    }

    public int decreaseLife(int damageAttacker, int defence){
        int result;
        damageAttacker = regulateDamage(damageAttacker);
        defence = regulateDefence(defence);
        result = damageAttacker - defence;
        if(result < 0)
            result = 0;
        return result;
    }


    private int regulateDamage(int damage){
        if(damage == 0)
            return 0;
        if(damage >= 90)
            return 100;
        if(damage <= 20)
            return 20;
        else if(damage <= 50)
            return 40;
        else if(damage <= 70)
            return 50;
        return 60;
    }

    private int regulateDefence(int defence){
        int critical = 100;
        if(defence < 30)//fail
            return 0;
        if(defence >= 90)
            return critical;
        if(defence <= 40)
            return 30;
        else if (defence <= 60)
            return 50;
        else if (defence <= 80)
            return 70;
        return 75;
        
    }

    public Color checkLifeBar(int life){
        if(life >140)
            return Color.GREEN;
        else if(life > 100)
            return Color.YELLOW;
        return Color.RED;
    }


} 

    

    
