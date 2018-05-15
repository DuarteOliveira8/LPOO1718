package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.Image;

/**
 * represents the main menu of the game
 */
public class Menu extends ScreenAdapter {
    /**
     * represents the valuable data of the game
     */
    GameData gameData;

    private Stage stage;

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

    Button playButton;
    Button lvlButton;
    Button settingsButton;

    /**
     * constructor of the Menu class
     * @param gameData represents the valuable data of the game
     */
    Menu(GameData gameData){
        this.gameData = gameData;

        playButton = new Button(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 200, 250, 250, 100, 100, "crossButton.png", "play.png");
        lvlButton = new Button(Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight()/2 - 150, 150, 150, 100, 100, "crossButton.png", "lvl.png");
        settingsButton = new Button(Gdx.graphics.getWidth()/2 + 300 + 75, Gdx.graphics.getHeight()/2 - 150, 150, 150, 100, 100, "crossButton.png", "settings.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

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
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
        super.render(delta);

        // Update the camera
        camera.update();
        gameData.getBatch().setProjectionMatrix(camera.combined);

        // Draw the texture
        gameData.getBatch().begin();
        gameData.getBatch().draw(gameData.levels.get(0).bg, 0,0);
        gameData.getBatch().draw(gameData.menuScene, 0, 0);
        gameData.getBatch().draw(gameData.logo, Gdx.graphics.getWidth() - gameData.logo.getWidth() - 150, Gdx.graphics.getHeight() - 225);
        playButton.draw(gameData.getBatch(),0);
        lvlButton.draw(gameData.getBatch(),0);
        settingsButton.draw(gameData.getBatch(),0);
        gameData.getBatch().end();

//        if(button1.hit(Gdx.input.getX(), Gdx.input.getY(), Gdx.input.isTouched()) != null){
//            hide();
//            gameData.
//        }
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
