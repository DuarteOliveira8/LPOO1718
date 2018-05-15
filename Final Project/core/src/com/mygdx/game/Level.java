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
    Texture bg;

    /**
     * scene of the level
     */
    Texture scene;

    /**
     * scene of the level
     */
    Texture floor;

    Level(String bgPath, String scenePath, String floorPath, int distance) {
        bg = new Texture(bgPath);
        scene = new Texture(scenePath);
        floor = new Texture(floorPath);
        totalDistance = distance;
    }

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