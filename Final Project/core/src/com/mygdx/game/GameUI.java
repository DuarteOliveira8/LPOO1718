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
    Level level;
    /**
     * represents an instance of the class HUD
     */
    HUD hud;
    /**
     * represents the valuable data of the game
     */
    GameData gameData;
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
    private static final float PIXEL_TO_METER = (float) (16.0/1920.0);
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
        infiniteScene = (infiniteScene + 3) % 1920;
        infiniteFloor = (infiniteFloor + 7) % 1920;

        // Update the camera
        camera.update();
        gameData.getBatch().setProjectionMatrix(camera.combined);

        // Draw the texture
        gameData.getBatch().begin();
        gameData.getBatch().draw(gameData.levels.get(0).bg, 0,0);
        gameData.getBatch().draw(gameData.levels.get(0).scene, 0 - infiniteScene,0);
        gameData.getBatch().draw(gameData.levels.get(0).scene, 1920 - infiniteScene,0);
        gameData.getBatch().draw(gameData.levels.get(0).floor, 0 - infiniteFloor,0);
        gameData.getBatch().draw(gameData.levels.get(0).floor, 1920 - infiniteFloor,0);
        //gameData.getBatch().draw(gameData.block.skin, 250, 204 + gameData.block.y + gameData.block.jumpingY, 100, 100);
        gameData.getBatch().draw(gameData.block.skinRegion, 250, 204 + gameData.block.y + gameData.block.jumpingY, 50, 50, 100, 100, 1, 1, gameData.block.angle);
        gameData.getBatch().end();

        if((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !gameData.block.isJumping && !gameData.block.isDropping)
            gameData.block.isJumping = true;

        if(gameData.block.isJumping || gameData.block.isDropping)
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
}
