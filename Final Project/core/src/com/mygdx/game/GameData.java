package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import java.util.ArrayList;
import static com.mygdx.game.GameData.GameState.*;


/**
 * represents all the important info and data to the game
 */
public class GameData {
    /**
     * enum types used to the define the current state of the program
     */
    public enum GameState {MENU, GAME, SETTINGS, PAUSED, BLOCKSELECTOR, LEVELSELECTOR, GAMEOVER, NEWGAME, LEVELCOMPLETE, TUTORIAL;}
    /**
     * current state of the app
     */
    private GameState gameState;
    /**
     * allows us to have different input processors for each screen
     */
    private InputMultiplexer inputMultiplexer;
    /**
     * array that stores all the levels from the game
     */
    private ArrayList<Level> levels;
    /**
     * current level number
     */
    private int currentLevelNo;
    /**
     * the program's batch where the textures are drawn
     */
    private SpriteBatch batch;
    /**
     *
     */
    private boolean isTransitioning;

    /**
     * GameData constructor
     */
    GameData(){
        levels = new ArrayList<Level>();
        batch = new SpriteBatch();
        isTransitioning = false;

        gameState = MENU;

        loadLevels();

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * functions that loads all the level scenarios and creates the levels, storing them in the array
     */
    private void loadLevels(){
        currentLevelNo = 1;
        LevelScenario lightForestScenario = new LevelScenario("lightForestBG.jpg", "lightForestScene.png", "level1.tmx");
        LevelScenario cityScenario = new LevelScenario("cityBG.jpg", "cityScene.png","level2.tmx");
        LevelScenario darkForestScenario = new LevelScenario("darkForestBG.jpg", "darkForestScene.png", "level3.tmx");
        levels.add(new Level(this, lightForestScenario, 11000, Touchable.enabled));
        levels.add(new Level(this, cityScenario, 11000, Touchable.disabled));
        levels.add(new Level(this, darkForestScenario,  11000, Touchable.disabled));
    }

    /**
     * function that sets the new skin picked by the player
     * @param skinPath filepath to the new string texture
     */
    public void setNewSkin(String skinPath){
        for(Level level : levels) {
            level.getBlock().setSkin(new Texture(skinPath));
            level.getBlock().setTexture(level.getBlock().getSkin());
        }
    }


    /**
     * adds a new stage to the index multiplier
     * @param stage the stage to be added
     */
    public void addStage(Stage stage){
        inputMultiplexer.addProcessor(stage);
    }

    /**
     * @return program's sprite batch
     */
    public SpriteBatch getBatch(){
        return batch;
    }

    /**
     * @return current app state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState sets a new app state
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * @return the level array
     */
    public ArrayList<Level> getLevels() {
        return levels;
    }

    /**
     * @param currentLevelNo the new level number
     */
    public void setCurrentLevelNo(int currentLevelNo) {
        this.currentLevelNo = currentLevelNo;
    }

    /**
     * @return the current level
     */
    public Level getCurrentLevel(){
        return levels.get(currentLevelNo - 1);
    }

    /**
     * @return if any menu is transitioning
     */
    public boolean isTransitioning() {
        return isTransitioning;
    }

    /**
     * @param transitioning the new value
     */
    public void setTransitioning(boolean transitioning) {
        isTransitioning = transitioning;
    }

    /**
     * @return the current level number
     */
    public int getCurrentLevelNo() {
        return currentLevelNo;
    }


}
