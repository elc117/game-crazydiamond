package com.mygdx.game;
import java.util.ArrayList;
public class CoronaQuotes {
    Utils util = new Utils();
    public String returnCoronaQuote(int coronaAttack, int globuloAttack, int coronaHit, int globuloHit){
        int random = util.random(4);
        if(globuloHit < 30)
            return checkGlobuloAim(random);
        else if(coronaHit < 30)
            return checkCoronaAim(random);
        else if(globuloAttack >= 90)
            return checkGlobuloDamage(random);
        return checkCoronaDamage(random, coronaAttack);


    }

    public String checkGlobuloAim(int random){
        switch (random) {
            case 0:
                return "      Bad aim, huh?";
            case 1:
                return "      Are you ok?";
            case 2:
                return " You drop something";
            case 3:
                return "     That's... bad";
            case 4:
                return "     Maybe?";
        }
        return null;
        
    }
    public String checkCoronaAim(int random){
        switch (random) {
            case 0:
                return "      He is bad";
            case 1:
                return "      Is he ok?";
            case 2:
                return "    He is... busy";
            case 3:
                return " Oh, that is something";
            case 4:
                return "     Maybe?";
        }
        return null;
        
    }
    public String checkCoronaDamage(int random, int damage){
        if(damage < 90){
            switch (random) {
                case 0:
                    return "    Oh, he is fine";
                case 1:
                    return "    Corona is happy";
                case 2:
                    return "      Boom, Corona";
                case 3:
                    return "       Looks him";
                case 4:
                    return "    A little damage";
            }
        }
        else{
            switch (random) {
                case 0:
                    return "He has a passionate air";
                case 1:
                    return " Corona will kill you";
                case 2:
                    return "       BOOM, Corona";
                case 3:
                    return "    Don't look at him";
                case 4:
                    return "A little big damage";
            }
        }
        return null;
    }
    public String checkGlobuloDamage(int random){
            switch (random) {
                case 0:
                    return "        ALL IN";
                case 1:
                    return "    Corona will boom";
                case 2:
                    return "        Kaboom";
                case 3:
                    return "      You look nice";
                case 4:
                    return "A little big damage";
            }
        return null;
    }

}
