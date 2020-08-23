package com.mygdx.game;
public class CoronaQuotes {
    Utils util = new Utils();

    String globuloAim[] = new String[]{
    "     Bad aim, huh?", "      Are you ok?", "  You drop something", 
    "     That's... bad", "     Maybe?"};

    String globuloMira[] = new String[]{
    "     Que mira ruim", "    Voce esta bem?", "  Voce deixou algo cair", 
    "  Isso deve ser ruim...", "     Talvez?"};

    String coronaAim[] = new String[]{
        "       He is bad", "      Is he ok?", "    He is... busy", 
        " Oh, that is something", "     Maybe?"};

    String coronaMira[] = new String[]{
        "      Ele ta mal", "      Ele ta bem?", "  Ele parece preocupado", 
        "     Oh, isso sim", "     Maybe?"};

    String coronaNormalDamage[] = new String[]{
        "    Oh, he is fine", "    Corona is happy", "      Boom, Corona", 
        "       Looks him", "    A little damage"};

    String coronaNormalDano[] = new String[]{
        "    Ah, ele ta bem", "   Corona ta animado", "      Boom, Corona", 
        "Mas olha o audacioso ali", "   Tá com pouco dano"};

    String coronaCriticalDamage[] = new String[]{
        "He has a passionate air", " Corona will kill you", "      BOOM, Corona", 
        "   Don't look at him", "A little big damage"};

    String coronaCriticalDano[] = new String[]{
        " Ele tem um olhar frio", " Corona vai te explodir", "      BOOM, Corona", 
        "Nao olhe para ele agora", "Ta com pouco dano"};

    String globuloDamage[] = new String[]{
        "        ALL IN", "    Corona will boom", "        Kaboom", 
        "     You look nice", "A little big damage"};

    String globuloDano[] = new String[]{
        "      Aposta tudo!", "   Corona vai explodir", "        Kaboom", 
        "    Voce ta com tudo", "    Voce ta bem ate"};






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
    
    public String retornaFalaDoCorona(int coronaAttack, int globuloAttack, int coronaHit, int globuloHit){
        int random = util.random(4);
        if(globuloHit < 30)
            return checkGlobuloMira(random);
        else if(coronaHit < 30)
            return checkCoronaMira(random);
        else if(globuloAttack >= 90)
            return checkGlobuloDano(random);
        return checkCoronaDano(random, coronaAttack);
    }

    public String checkGlobuloAim(int random){
       return globuloAim[random];
    }

    public String checkGlobuloMira(int random){
       return globuloMira[random];
    }

    public String checkCoronaAim(int random){
        return coronaAim[random];
    }

    public String checkCoronaMira(int random){
        return coronaMira[random];
    }

    public String checkCoronaDamage(int random, int damage){
        if(damage < 90)
            return coronaNormalDamage[random];
        return coronaCriticalDamage[random];
    }

    public String checkCoronaDano(int random, int damage){
        if(damage < 90)
            return coronaNormalDano[random];
        return coronaCriticalDano[random];
    }

    public String checkGlobuloDamage(int random){
        return globuloDamage[random];
    }

    public String checkGlobuloDano(int random){
        return globuloDano[random];
    }

}