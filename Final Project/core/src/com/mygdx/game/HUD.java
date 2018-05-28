package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * class that represents the interface displayed on top of
 * the game where the player can check some important info
 */
public class HUD extends Stage {
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    Button pauseButton;

    /**
     * constructor of the HUD class
     * @param gameData represents the valuable data of the game
     */
    HUD(final GameData gameData) {

        this.gameData = gameData;

        pauseButton = new Button((Gdx.graphics.getWidth()*1790)/1920, (Gdx.graphics.getHeight()*944)/1080, (Gdx.graphics.getWidth()*110)/1920, (Gdx.graphics.getHeight()*116)/1080, "pause.png");

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.gameState = GameData.GameState.MENU;
            }
        });

        gameData.getHUDStage().addActor(pauseButton);
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
        pauseButton.draw(gameData.getBatch(),0);
    }
}
