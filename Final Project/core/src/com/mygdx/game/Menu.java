package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * represents the main menu of the game
 */
public class Menu implements Screen {
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    /**
     * constructor of the Menu class
     * @param gameData represents the valuable data of the game
     */
    public void Menu(GameData gameData){
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
