package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

/**
 * represents all the important info and data to the game
 */
public class GameData {

    public enum GameState {
        MENU, GAME, SETTINGS, LEVELS, PAUSE, BLOCKSELECTOR, LEVELSELECTOR;
    }

    private GameState gameState = GameState.MENU;

    private InputMultiplexer inputMultiplexer;

    /**
     * number of lives (out of 3) the player has
     */
    private int lives;
    /**
     * main character
     */
    private Block  block;
    /**
     * array that stores all the levels from the game
     */
    private ArrayList<Level> levels;
    /**
     * current level number
     */
    int currentLevelNo;
    /**
     * scene texture of the main menu
     */
    private Texture menuScene;
    /**
     * scene texture of the main menu
     */
    private Texture logo;

    /**
     * the batch where the textures are drawn
     */
    SpriteBatch batch;

    private Stage menuStage;
    private Stage hudStage;
    private Stage settingsStage;
    private Stage blocksStage;
    private Stage levelSelectorStage;

    GameData(){

        lives = 3;
        levels = new ArrayList<Level>();
        batch = new SpriteBatch();
        loadTextures();
        block = new Block("lightForestBlock.png");
        levels.add(new Level("lightForestBG.jpg", "lightForestScene.png", "lightForestFloor.png", 0, Touchable.enabled));
        levels.add(new Level("cityBG.jpg", "cityScene.png","cityFloor.png",0, Touchable.disabled));
        levels.add(new Level("darkForestBG.jpg", "darkForestScene.png", "darkForestFloor.png",0, Touchable.disabled));

        inputMultiplexer = new InputMultiplexer();

        menuStage = new Stage(new ScreenViewport());
        hudStage = new Stage(new ScreenViewport());
        blocksStage = new Stage(new ScreenViewport());
        settingsStage = new Stage(new ScreenViewport());
        levelSelectorStage = new Stage(new ScreenViewport());

        inputMultiplexer.addProcessor(blocksStage);
        inputMultiplexer.addProcessor(settingsStage);
        inputMultiplexer.addProcessor(menuStage);
        inputMultiplexer.addProcessor(hudStage);
        inputMultiplexer.addProcessor(levelSelectorStage);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void loadTextures(){
        menuScene = new Texture("menuScene.png");
        logo = new Texture("logo.png");
    }

    public SpriteBatch getBatch(){
        return batch;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        this.inputMultiplexer = inputMultiplexer;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public Texture getMenuScene() {
        return menuScene;
    }

    public void setMenuScene(Texture menuScene) {
        this.menuScene = menuScene;
    }

    public Texture getLogo() {
        return logo;
    }

    public void setLogo(Texture logo) {
        this.logo = logo;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public void setMenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

    public Stage getHudStage() {
        return hudStage;
    }

    public void setHudStage(Stage hudStage) {
        this.hudStage = hudStage;
    }

    public Stage getBlocksStage() {
        return blocksStage;
    }

    public void setBlocksStage(Stage blocksStage) {
        this.blocksStage = blocksStage;
    }

    public Stage getSettingsStage() {

        return settingsStage;
    }

    public void setSettingsStage(Stage settingsStage) {
        this.settingsStage = settingsStage;
    }

    public Stage getLevelSelectorStage() {
        return levelSelectorStage;
    }

    public void setLevelSelectorStage(Stage levelSelectorStage) {
        this.levelSelectorStage = levelSelectorStage;
    }

    public int getCurrentLevelNo() {
        return currentLevelNo;
    }

    public void setCurrentLevelNo(int currentLevelNo) {
        this.currentLevelNo = currentLevelNo;
    }

    public Level getCurrentLevel(){
        return levels.get(currentLevelNo - 1);
    }
}
