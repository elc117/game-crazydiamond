package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleButton {

    private Sprite skin;

    private boolean clicked = false;

    private float x;
    private float y;
 

    public SimpleButton(Texture texture, float x, float y, float width, float height) {
        skin = new Sprite(texture); // your image
        skin.setPosition(x, y);
        skin.setSize(width, height);
        this.x = (-x)-20;
        this.y = (-y)+420;
        
    }

    public void update (SpriteBatch batch, float input_x, float input_y) {
        if(clicked == true)
            clicked = false;
        checkIfClicked(input_x, input_y); 
        skin.draw(batch); // draw the button
    }

    private void checkIfClicked (float ix, float iy) {
        if (ix > skin.getX()+x && ix < skin.getX()+x  + skin.getWidth()) {
            if (iy > skin.getY()+y && iy < skin.getY()+y + skin.getHeight() && Gdx.input.isTouched()) {
                // the button was clicked, perform an action
                clicked = true;
                System.out.println("Button clicked !");
            }
        }
    }

    public boolean clicked(){
        return clicked;
    }

}