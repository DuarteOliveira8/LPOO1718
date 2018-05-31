package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.mygdx.game.GameData.GameState;

/**
 * represents the main menu of the game
 */
public class Menu extends ScreenAdapter {
    /**
     * represents the valuable data of the game
     */
    private GameData gameData;

    private Button playButton;
    private Button lvlButton;
    private Button settingsButton;
    private Button exitButton;


    private static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    private static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);

    /**
     * constructor of the Menu class
     * @param gameData represents the valuable data of the game
     */
    Menu(final GameData gameData){
        this.gameData = gameData;

        playButton = new Button((int)(838*WIDTH_CONVERTER), (int)(280*HEIGHT_CONVERTER), (int)(245*WIDTH_CONVERTER), (int)(258*HEIGHT_CONVERTER),  "play.png");

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameState.GAME);
            }
        });

        exitButton = new Button((int)(1400*WIDTH_CONVERTER), (int)(25*HEIGHT_CONVERTER), (int)(442*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "exitbutton.png");
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        lvlButton = new Button((int)(499*WIDTH_CONVERTER), (int)(300*HEIGHT_CONVERTER), (int)(204*WIDTH_CONVERTER), (int)(215*HEIGHT_CONVERTER),  "level.png");
        lvlButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameState.LEVELSELECTOR);
            }
        });

        settingsButton = new Button((int)(1218*WIDTH_CONVERTER), (int)(300*HEIGHT_CONVERTER), (int)(204*WIDTH_CONVERTER), (int)(215*HEIGHT_CONVERTER),  "settings.png");
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameState.SETTINGS);
            }
        });

        gameData.getMenuStage().addActor(playButton);
        gameData.getMenuStage().addActor(lvlButton);
        gameData.getMenuStage().addActor(settingsButton);
        gameData.getMenuStage().addActor(exitButton);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
        super.render(delta);

        gameData.getBatch().begin();
        gameData.getBatch().draw(gameData.getLevels().get(0).getLevelScenario().getBg(), 0,0,Gdx.graphics.getWidth(), 1440*HEIGHT_CONVERTER);
        gameData.getBatch().draw(gameData.getMenuScene(), 0, 0, Gdx.graphics.getWidth(), 1440*HEIGHT_CONVERTER);
        gameData.getBatch().draw(gameData.getLogo(), 420*WIDTH_CONVERTER, 737*HEIGHT_CONVERTER, 1074*WIDTH_CONVERTER, 218*HEIGHT_CONVERTER);
        playButton.draw(gameData.getBatch(),0);
        lvlButton.draw(gameData.getBatch(),0);
        settingsButton.draw(gameData.getBatch(),0);
        exitButton.draw(gameData.getBatch(),0);
        gameData.getBatch().end();
    }

    public void disableButtons(){
        playButton.setTouchable(disabled);
        lvlButton.setTouchable(disabled);
        settingsButton.setTouchable(disabled);
        exitButton.setTouchable(disabled);
    }

    public void enableButtons(){
        playButton.setTouchable(enabled);
        lvlButton.setTouchable(enabled);
        settingsButton.setTouchable(enabled);
        exitButton.setTouchable(enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int width, int height){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show(){
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    public Button getLvlButton() {
        return lvlButton;
    }

    public void setLvlButton(Button lvlButton) {
        this.lvlButton = lvlButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(Button settingsButton) {
        this.settingsButton = settingsButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }
}
