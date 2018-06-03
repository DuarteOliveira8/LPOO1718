package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.mygdx.game.Menu.WIDTH_CONVERTER;
import static com.mygdx.game.Menu.HEIGHT_CONVERTER;
import static com.mygdx.game.GameData.GameState.*;

/**
 * class that represents the interface displayed on top of the game where the player can check important info
 */
public class HUD extends Stage {
    /**
     * represents the valuable data of the game
     */
    private GameData gameData;
    /**
     * represents the hud pause button
     */
    private Button pauseButton;
    /**
     * texture of the progress bar frame
     */
    private Texture progressBarFrame;
    /**
     * texture of the progress bar
     */
    private Texture progressBar;


    /**
     * HUD constructor
     * @param gameData represents the valuable data of the game
     */
    HUD(final GameData gameData) {
        this.gameData = gameData;
        loadTextures();

        pauseButton = new Button((int)(1790*WIDTH_CONVERTER), (int)(944*HEIGHT_CONVERTER), (int)(110*WIDTH_CONVERTER), (int)(116*HEIGHT_CONVERTER), "pause.png");

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(PAUSED);
                gameData.setTransitioning(true);
            }
        });

        this.addActor(pauseButton);
        gameData.addStage(this);
    }

    private void loadTextures(){
        progressBarFrame = new Texture("progressBarFrame.png");
        progressBar = new Texture("progressBar.png");
    }

    /**
     * disables the pause button
     */
    public void disableButtons(){
        pauseButton.setTouchable(disabled);
    }

    /**
     * enables the pause button
     */
    public void enableButtons(){
        pauseButton.setTouchable(enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(){
        pauseButton.draw(gameData.getBatch(),0);
        gameData.getBatch().draw(progressBarFrame, 685*WIDTH_CONVERTER, 1037*HEIGHT_CONVERTER, 551*WIDTH_CONVERTER, 31*HEIGHT_CONVERTER);
        gameData.getBatch().draw(progressBar, 691*WIDTH_CONVERTER, 1043*HEIGHT_CONVERTER, 538*gameData.getCurrentLevel().getScorePercentage()*WIDTH_CONVERTER, 19*HEIGHT_CONVERTER);
    }

    public Button getPauseButton() {
        return pauseButton;
    }
}
