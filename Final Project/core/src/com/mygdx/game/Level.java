package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * class that represents a different level from the game
 */
public class Level {

    /**
     * traveled distance
     */
    private int distance;
    /**
     * total distance of the level
     */
    private int maxDistance;

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

    /**
     * defines if the level is available
     */
    Touchable levelLock;


    Level(String bgPath, String scenePath, String floorPath, int distance, Touchable isEnabled) {
        bg = new Texture(bgPath);
        scene = new Texture(scenePath);
        floor = new Texture(floorPath);
        maxDistance = distance;
        distance = 0;
        levelLock = isEnabled;

    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxlDistance(int maxDistance) {
        this.maxDistance = maxDistance;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Touchable getLevelLock() {
        return levelLock;
    }

    public void setLevelLock(Touchable levelLock) {
        this.levelLock = levelLock;
    }
}