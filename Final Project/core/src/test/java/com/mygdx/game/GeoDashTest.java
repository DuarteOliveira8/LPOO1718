package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Touchable;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeoDashTest {

    @Test
    public void unlockLevel() {
        LevelScenario level1 = new LevelScenario("lightForestBG.jpg", "lightForestScene.png", "level1.tmx");
        LevelScenario level2 = new LevelScenario("cityBG.jpg", "cityScene.png","level2.tmx");
        LevelScenario level3 = new LevelScenario("darkForestBG.jpg", "darkForestScene.png", "level3.tmx");
        Level level1 = new Level(this, lightForestScenario, 11000, Touchable.enabled);
        Level level2 = new Level(this, cityScenario, 22000, Touchable.disabled);
        Level level3 = new Level(this, darkForestScenario,  11000, Touchable.disabled);
    }

    @Test
    public void addDistance() {
    }
}