package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SimpleMusic {
    private Music music;

    public SimpleMusic(float volume, boolean looping, String location){
        music = Gdx.audio.newMusic(Gdx.files.internal(location));
        music.setLooping(looping);
        music.setVolume(volume);
    }

    public void stop(){
        music.stop();
    }

    public void play(){
        music.play();
    }
}