package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;

/**
 * class that represents the interface displayed on top of
 * the game where the player can check some important info
 */
public class HUD extends Stage {
    /**
     * represents the valuable data of the game
     */
    private GameData gameData;

    private Button pauseButton;

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
                gameData.setGameState(GameData.GameState.MENU);
            }
        });

        gameData.getHudStage().addActor(pauseButton);
    }

    public void disableButtons(){
        pauseButton.setTouchable(disabled);
    }

    public void enableButtons(){
        pauseButton.setTouchable(enabled);
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

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(Button pauseButton) {
        this.pauseButton = pauseButton;
    }
}
