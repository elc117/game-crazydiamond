package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver.TextureAtlasImageResolver;

public class SimpleAnimation {
    Animation animation;
    TextureAtlas texture;

    public SimpleAnimation(String location){
        texture = new TextureAtlas(Gdx.files.internal(location));
        animation = new Animation(1f/6f, texture.getRegions());
    }

    public void update(SpriteBatch batch, float elapsedTime, float x, float y){
        batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime, true), x, y);
    }
    
}