package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;

/**
 * class where you can alter game settings including customizing the block
 */
public class Settings extends ScreenAdapter{
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    /**
     * constructor of the Settings class
     * @param gameData represents the valuable data of the game
     */
    Settings(GameData gameData){
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
