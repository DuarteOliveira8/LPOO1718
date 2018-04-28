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
