package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * class where you can alter game settings including customizing the block
 */
public class Settings implements Screen{
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    /**
     * constructor of the Settings class
     * @param gameData represents the valuable data of the game
     */
    public void Settings(GameData gameData){
        this.gameData = gameData;
    }

    @Override
    public void dispose(){
    }

    @Override
    public void hide(){
    }

    @Override
    public void pause(){
    }

    @Override
    public void render(float delta){
    }

    @Override
    public void resize(int width, int height){
    }

    @Override
    public void resume(){
    }

    @Override
    public void show(){
    }
}
