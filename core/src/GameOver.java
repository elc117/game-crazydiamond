package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver {
    String scoreStr;
    Utils utils = new Utils();

    public void update(int coronaLife, int globuloLife, boolean portuguese, SpriteBatch batch, BitmapFont font){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        scoreStr = Integer.toString(utils.scoreGet(coronaLife, globuloLife));

        batch.begin();

        font.draw(batch, utils.matchResult(globuloLife, coronaLife, portuguese), -140, 120);
        
        if(!portuguese)
            font.draw(batch, "Score:", -190, 21);
        
        else
            font.draw(batch, "Pontos:", -190, 21);

        font.draw(batch, scoreStr,  50, 21);

        if(!portuguese)
            font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth()/7-450, Gdx.graphics.getWidth()/8 -160);

        else
            font.draw(batch, "Aperte enter para reiniciar.", Gdx.graphics.getWidth()/7-550, Gdx.graphics.getWidth()/8 -160);
            		
        
        batch.end();
    }
}