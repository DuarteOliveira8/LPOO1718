package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * represents the interface where the game is running
 */
public class GameUI extends ScreenAdapter {
    /**
     * represents an instance of the class Level
     */
    private Level level;
    /**
     * represents an instance of the class HUD
     */
    private HUD hud;

    private int levelNo;
    /**
     * represents the valuable data of the game
     */
    private GameData gameData;
    /**
     * viewport width in meters
     */
    private static final int VIEWPORT_WIDTH = 16;
    /**
     * viewport width in meters
     */
    private static final int VIEWPORT_HEIGHT = 9;
    /**
     * allows us to convert from pixels to meters
     */
    private static final float PIXEL_TO_METER = (float) (16.0/Gdx.graphics.getWidth());
    /**
     * the screen's camera
     */
    private final OrthographicCamera camera;

    private static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    private static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);

    /**
     * constructor of the GameUI class
     * @param gameData represents the valuable data of the game
     */
    GameUI(GameData gameData, int levelNo){
        this.gameData = gameData;
        gameData.setCurrentLevelNo(levelNo);
        level = gameData.getCurrentLevel();
        hud = new HUD(gameData);
        this.levelNo = levelNo;

        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER);
        camera.position.set(new Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0)); // middle of the viewport
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
     * allows to draw the infinite background
     */
    int infiniteScene = 0;
    int infiniteFloor = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
        super.render(delta);
        infiniteScene = (infiniteScene + (Gdx.graphics.getWidth()*4)/1920) % Gdx.graphics.getWidth();
        infiniteFloor = (infiniteFloor + (Gdx.graphics.getWidth()*10)/1920) % Gdx.graphics.getWidth();

        // Update the camera
        camera.update();
        gameData.getBatch().setProjectionMatrix(camera.combined);

        // Draw the texture
        gameData.getBatch().begin();
        gameData.getBatch().draw(level.getBg(), 0,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(level.getScene(), 0 - infiniteScene,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(level.getScene(), Gdx.graphics.getWidth() - infiniteScene,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(level.getFloor(), 0 - infiniteFloor,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(level.getFloor(), Gdx.graphics.getWidth() - infiniteFloor,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(gameData.getBlock().getSkinRegion(), (int) (250*WIDTH_CONVERTER), 204*HEIGHT_CONVERTER + gameData.getBlock().getY() + gameData.getBlock().getJumpingY(), (100*WIDTH_CONVERTER)/2, (100*WIDTH_CONVERTER)/2, 100*WIDTH_CONVERTER, 100*WIDTH_CONVERTER, 1, 1, gameData.getBlock().getAngle());
        hud.draw();
        gameData.getBatch().end();

        if((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !gameData.getBlock().isJumping() && !gameData.getBlock().isDropping())
            gameData.getBlock().setJumping(true);

        if(gameData.getBlock().isJumping() || gameData.getBlock().isDropping())
            gameData.getBlock().jump();

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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public static int getViewportWidth() {
        return VIEWPORT_WIDTH;
    }

    public static int getViewportHeight() {
        return VIEWPORT_HEIGHT;
    }

    public static float getPixelToMeter() {
        return PIXEL_TO_METER;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public int getInfiniteScene() {
        return infiniteScene;
    }

    public void setInfiniteScene(int infiniteScene) {
        this.infiniteScene = infiniteScene;
    }

    public int getInfiniteFloor() {
        return infiniteFloor;
    }

    public void setInfiniteFloor(int infiniteFloor) {
        this.infiniteFloor = infiniteFloor;
    }
}
