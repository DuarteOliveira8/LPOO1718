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

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int width, int height){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(){
    }
}
