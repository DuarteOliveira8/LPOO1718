package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * represents all the important info and data to the game
 */
public class GameData {
    /**
     * traveled distance
     */
    int distance;
    /**
     * number of lives (out of 3) the player has
     */
    int lives;
    /**
     * main character
     */
    Block  block;
    /**
     * array that stores all the levels from the game
     */
    ArrayList<Level> levels;
    /**
     * scene texture of the main menu
     */
    Texture menuScene;
    /**
     * scene texture of the main menu
     */
    Texture logo;
    /**
     * the batch where the textures are drawn
     */
    SpriteBatch batch;

    GameData(){
        lives = 3;
        levels = new ArrayList<Level>();
        batch = new SpriteBatch();
        loadTextures();
        block = new Block("lightForestBlock.png");
        levels.add(new Level("lightForestBG.jpg", "lightForestScene.png", "lightForestFloor.png", 0));
        levels.add(new Level("darkForestBG.jpg", "darkForestScene.png", "darkForestFloor.png",0));
        levels.add(new Level("cityBG.jpg", "cityScene.png","cityFloor.png",0));
    }

    public void loadTextures(){
        menuScene = new Texture("menuScene.png");
        logo = new Texture("logo.png");
    }

    public SpriteBatch getBatch(){
        return batch;
    }
}
