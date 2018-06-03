package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.Map;
import java.util.TreeMap;

/**
 * represents the abstract menu superclass
 */
public abstract class Menu extends ScreenAdapter {
    /**
     * variable to access the game's data
     */
    protected GameData gameData;
    /**
     * map to store all the menu's buttons
     */
    protected Map<String,Button> buttons;
    /**
     * map to store all the menu's textures
     */
    protected Map<String,Texture> textures;
    /**
     * menu's stage
     */
    protected Stage stage;
    /**
     * allows us to adapt the width of objects depending on the current device's screen resolution
     */
    public static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    /**
     * allows us to adapt the height of objects depending on the current device's screen resolution
     */
    public static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);

    /**
     * constructor of the Menu class
     * @param gameData represents the valuable data of the game
     */
    Menu(final GameData gameData){
        this.gameData = gameData;
        buttons = new TreeMap<String,Button>();
        textures = new TreeMap<String,Texture>();
        createStage();
    }

    /**
     * creates the menu's stage
     */
    private void createStage(){
        stage = new Stage(new ScreenViewport());
        gameData.addStage(stage);
    }

    /**
     * creates all the menu buttons
     */
    protected abstract void createButtons();

    /**
     * creates all the menu textures
     */
    protected abstract void createTextures();

    /**
     * creates all the menu button listeners
     */
    protected abstract void createListeners();

    /**
     * adds all actors (the buttons) to the menu's stage
     */
    protected void addActorsToStage(){
        for(Map.Entry<String, Button> button : buttons.entrySet())
            stage.addActor(button.getValue());
    }

    /**
     * draws all the menu buttons
     */
    protected void drawButtons(){
        for(Map.Entry<String, Button> button : buttons.entrySet())
            button.getValue().draw(gameData.getBatch(), 0);
    }

    /**
     * disables all the menu buttons
     */
    public void disableButtons(){
        for(Map.Entry<String, Button> button : buttons.entrySet())
            button.getValue().setTouchable(Touchable.disabled);
    }

    /**
     * enables all the menu buttons
     */
    public void enableButtons(){
        for(Map.Entry<String, Button> button : buttons.entrySet())
            button.getValue().setTouchable(Touchable.enabled);
    }
}
