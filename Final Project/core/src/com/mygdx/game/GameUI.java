package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * represents the interface where the game is running
 */
public class GameUI implements Screen{
    /**
     * represents an instance of the class Level
     */
    Level level;
    /**
     * represents an instance of the class HUD
     */
    HUD hud;
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    /**
     * constructor of the GameUI class
     * @param gameData represents the valuable data of the game
     */
    public void GameUI(GameData gameData){
        this.gameData = gameData;
        level = new Level();
        hud = new HUD();
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
