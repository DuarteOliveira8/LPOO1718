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
    private int totalDistance;

    /**
     * background of the level
     */
    private Texture bg;

    /**
     * scene of the level
     */
    private Texture scene;

    /**
     * scene of the level
     */
    private Texture floor;

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

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Texture getBg() {
        return bg;
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }

    public Texture getScene() {
        return scene;
    }

    public void setScene(Texture scene) {
        this.scene = scene;
    }

    public Texture getFloor() {
        return floor;
    }

    public void setFloor(Texture floor) {
        this.floor = floor;
    }
}