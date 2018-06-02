package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
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
     * the screen's camera
     */
    private final OrthographicCamera camera;

    public static final float PPM = 500f;
    public static final float FPS = 1/60f;
    public static final float CAMERA_DELTA = 13f;

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
        //camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER);
        camera = new OrthographicCamera((Gdx.graphics.getWidth() / WIDTH_CONVERTER) / GameUI.PPM, Gdx.graphics.getHeight() / HEIGHT_CONVERTER / GameUI.PPM);
        camera.position.set(new Vector3((Gdx.graphics.getWidth()/2)/WIDTH_CONVERTER / GameUI.PPM, (Gdx.graphics.getHeight()/2)/HEIGHT_CONVERTER / GameUI.PPM, 0)); // middle of the viewport
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
        infiniteScene = (int) (infiniteScene + 4*WIDTH_CONVERTER) % Gdx.graphics.getWidth();
        infiniteFloor = (int) (infiniteFloor + 10*WIDTH_CONVERTER) % Gdx.graphics.getWidth();

        level = gameData.getCurrentLevel();

        // Draw the texture
        gameData.getBatch().begin();
        gameData.getBatch().draw(level.getLevelScenario().getBg(), 0,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(level.getLevelScenario().getScene(), 0 - infiniteScene,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(level.getLevelScenario().getScene(), Gdx.graphics.getWidth() - infiniteScene,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        //gameData.getBlock().update();
        //gameData.getBlock().draw(gameData.getBatch());
        hud.draw();
        gameData.getBatch().end();

        // Update the camera
        level.getLevelScenario().getMapRenderer().render();
        level.getLevelScenario().getDebugRenderer().render(gameData.getWorld(), camera.combined);
        camera.update();
        level.getLevelScenario().getMapRenderer().setView(camera);
        camera.position.x += CAMERA_DELTA / PPM;


        gameData.getBatch().begin();
        gameData.getBlock().update();
        gameData.getBlock().draw(gameData.getBatch());
        gameData.getBatch().end();



        gameData.getWorld().step(FPS,3,3);

        gameData.getBlock().getBody().setLinearVelocity(CAMERA_DELTA/PPM/FPS,gameData.getBlock().getBody().getLinearVelocity().y);


        //if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
           // gameData.getBlock().getBody().setLinearVelocity(CAMERA_DELTA/PPM/FPS, 3f);

        if((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !(gameData.getBlock().getCurrentState() == Block.State.JUMPING))
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
