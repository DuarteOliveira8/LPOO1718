package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * class that represents a different level from the game
 */
public class Level extends Stage {
    /**
     * total distance of the level
     */
    int totalDistance;

    /**
     * background of the level
     */
    Texture skin = new Texture("background_desert.png");

    /**
     * {@inheritDoc}
     */
    @Override
    public void act (float delta){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(){
    }
}