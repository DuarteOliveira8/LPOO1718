package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.Image;

import static com.mygdx.game.GameData.GameState;

/**
 * represents the main menu of the game
 */
public class Menu extends ScreenAdapter {
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
    private static final float PIXEL_TO_METER = (float) (16.0/Gdx.graphics.getWidth());
    /**
     * the screen's camera
     */
    private final OrthographicCamera camera;

    Button playButton;
    Button lvlButton;
    Button settingsButton;
    Button exitButton;


    /**
     * constructor of the Menu class
     * @param gameData represents the valuable data of the game
     */
    Menu(final GameData gameData){
        this.gameData = gameData;

        playButton = new Button((Gdx.graphics.getWidth()*838)/1920, (Gdx.graphics.getHeight()*280)/1080, (Gdx.graphics.getWidth()*245)/1920, (Gdx.graphics.getHeight()*258)/1080,  "play.png");

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.gameState = GameState.GAME;
            }
        });

        exitButton = new Button((Gdx.graphics.getWidth()*1400)/1920, (Gdx.graphics.getHeight()*25)/1080, (Gdx.graphics.getWidth()*442)/1920, (Gdx.graphics.getHeight()*130)/1080,  "exitbutton.png");
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        lvlButton = new Button((Gdx.graphics.getWidth()*499)/1920, (Gdx.graphics.getHeight()*300)/1080, (Gdx.graphics.getWidth()*204)/1920, (Gdx.graphics.getHeight()*215)/1080,  "level.png");
        settingsButton = new Button((Gdx.graphics.getWidth()*1218)/1920, (Gdx.graphics.getHeight()*300)/1080, (Gdx.graphics.getWidth()*204)/1920, (Gdx.graphics.getHeight()*215)/1080,  "settings.png");


        gameData.getMenuStage().addActor(playButton);
        gameData.getMenuStage().addActor(lvlButton);
        gameData.getMenuStage().addActor(settingsButton);
        gameData.getMenuStage().addActor(exitButton);

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

        // Draw the texture Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080
        gameData.getBatch().begin();
        gameData.getBatch().draw(gameData.levels.get(0).bg, 0,0,Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.menuScene, 0, 0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.logo, (Gdx.graphics.getWidth()*420)/1920, (Gdx.graphics.getWidth()*737)/1920, (Gdx.graphics.getWidth()*1074)/1920, (Gdx.graphics.getHeight()*218)/1080);
        playButton.draw(gameData.getBatch(),0);
        lvlButton.draw(gameData.getBatch(),0);
        settingsButton.draw(gameData.getBatch(),0);
        exitButton.draw(gameData.getBatch(),0);
        gameData.getBatch().end();


        /*if(playButton.hit(Gdx.input.getX(), Gdx.input.getY(), Gdx.input.justTouched()) != null){
            System.out.println("ENTREI");
            gameData.gameState = GAME;
        }*/
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
