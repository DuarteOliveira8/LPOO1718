package com.mygdx.game;

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

    public void GameData(){
        lives = 3;
        levels = new ArrayList<Level>();
    }
}
