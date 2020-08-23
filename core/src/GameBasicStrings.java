package com.mygdx.game;
import java.util.ArrayList;

public class GameBasicStrings {
    ArrayList<String> strings = new ArrayList();
    ArrayList<String> palavras = new ArrayList();
    GameBasicStrings(){
        strings.add("Mission: Defeat Corona");
        strings.add("A - Attack : D - Defend");
        strings.add("   Attack Successfull");
        strings.add("   Critical Attack!");
        strings.add("      Attack Fail");
        strings.add("   Defense Successfull");
        strings.add("   Defense Perfectly");
        strings.add("      Defense Fail");
        strings.add("Corona Critical Attack!");
        strings.add("  Corona Defense Fail");
        strings.add(" Corona Defense Success");
        strings.add("Corona Defend Perfectly");
        strings.add("Corona Attack Successfull");
        strings.add("Corona Attack Fail");

        palavras.add("Missao: Derrote Corona");
        palavras.add("A - Atacar : D - Defender");
        palavras.add("   Ataque bem sucedido");
        palavras.add("     Ataque crítico!");
        palavras.add("   Ataque mal sucedido");
        palavras.add("   Defesa bem sucedida");
        palavras.add("    Defesa perfeita");
        palavras.add("  Defesa mal sucedida");
        palavras.add("Ataque crítico de Corona");
        palavras.add(" Corona se defendeu mal");
        palavras.add("   Corona se defendeu");
        palavras.add(" Corona defendeu tudo");
        palavras.add("   Corona te acertou");
        palavras.add("Corona Errou");


    }

    public String getString(int indice){
        for(int i = 0; i < strings.size(); i++){
            if(i == indice)
                return strings.get(i);
        }
        return null;
    }

    public String pegaString(int indice){
        for(int i = 0; i < palavras.size(); i++){
            if(i == indice)
                return palavras.get(i);
        }
        return null;
    }

    public int nextString(String str){
        if(str == "Game Start   -   [ENTER]")
            return 0;
        return 1;
    }
    
    public int proximaString(String str){
        if(str == "Iniciar Jogo   -   [ENTER]")
            return 0;
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
 
}