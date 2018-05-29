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

    /**
     * constructor of the GameUI class
     * @param gameData represents the valuable data of the game
     */
    GameUI(GameData gameData, int levelNo){
        this.gameData = gameData;
        level = gameData.levels.get(levelNo - 1);
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
        gameData.getBatch().draw(gameData.levels.get(levelNo - 1).getBg(), 0,0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.levels.get(levelNo - 1).getScene(), 0 - infiniteScene,0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.levels.get(levelNo - 1).getScene(), Gdx.graphics.getWidth() - infiniteScene,0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.levels.get(levelNo - 1).getFloor(), 0 - infiniteFloor,0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.levels.get(levelNo - 1).getFloor(), Gdx.graphics.getWidth() - infiniteFloor,0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.block.getSkinRegion(), ((Gdx.graphics.getWidth()*250)/1920), ((Gdx.graphics.getHeight()*204)/1080) + gameData.block.getY() + gameData.block.getJumpingY(), ((Gdx.graphics.getWidth()*100)/1920)/2, ((Gdx.graphics.getWidth()*100)/1920)/2, ((Gdx.graphics.getWidth()*100)/1920), ((Gdx.graphics.getWidth()*100)/1920), 1, 1, gameData.block.getAngle());
        hud.draw();
        gameData.getBatch().end();

        if((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !gameData.block.isJumping() && !gameData.block.isDropping())
            gameData.block.setJumping(true);

        if(gameData.block.isJumping() || gameData.block.isDropping())
            gameData.block.jump();

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
