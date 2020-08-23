package com.mygdx.game;
import java.util.Random;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;

public class Utils{
    ArrayList<String> strings = new ArrayList();
    Random rand = new Random();
    
    public int random(int randint){
        return rand.nextInt(randint);
    }

    public boolean permitComment(int commentID){
        return commentID != 1;
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

    public int scoreGet(int enemyLife, int characterLife){
        return (characterLife * 10) + ((300 - enemyLife)*10) ;
    }

    public String matchResult(int characterLife, int enemyLife, boolean portuguese){
        if(characterLife <= 0 && enemyLife <= 0){
            if(!portuguese)
                return "  Draw!";
            return    " Empate!";
        }
        else if(enemyLife <= 0){
            if(!portuguese)
                return "You Win!";
            return     "Vitoria!";
        }
        else{
            if(!portuguese)
                return "Game Over!";
            return " Derrota!";
        }
    }


} 

    

    
