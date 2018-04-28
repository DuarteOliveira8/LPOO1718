package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * class that represents the interface displayed on top of
 * the game where the player can check some important info
 */
public class HUD extends Stage {
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    /**
     * constructor of the HUD class
     * @param gameData represents the valuable data of the game
     */
    public void HUD(GameData gameData){
        this.gameData = gameData;
    }

    @Override
    public void act (float delta){
    }

    @Override
    public void draw(){
    }
}
