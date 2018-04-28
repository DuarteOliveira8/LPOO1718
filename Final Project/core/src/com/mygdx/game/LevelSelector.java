package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * menu where the player can choose what level to play
 */
public class LevelSelector implements Screen {
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    /**
     * constructor of the LevelSelector class
     * @param gameData represents the valuable data of the game
     */
    public void LevelSelector(GameData gameData){
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
