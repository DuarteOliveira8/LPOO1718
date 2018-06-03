package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import static com.mygdx.game.Menu.WIDTH_CONVERTER;
import static com.mygdx.game.Menu.HEIGHT_CONVERTER;
import static com.mygdx.game.GameData.GameState.*;

/**
 * represents the interface where the game is running
 */
public class GameUI extends ScreenAdapter {
    /**
     * represents the game's head-up display
     */
    private HUD hud;
    /**
     * represents the valuable data of the game
     */
    private GameData gameData;
    /**
     * the screen's camera
     */
    private final OrthographicCamera camera;
    /**
     * pixels per meter
     */
    public static final float PPM = 500f;
    /**
     * frames per second
     */
    public static final float FPS = 1/60f;
    /**
     * game camera movement per render
     */
    public static final float CAMERA_DELTA = 13f;
    /**
     * value used to draw the game scene with a parallax effect
     */
    private int infiniteScene = 0;

    /**
     * GameUI constructor
     * @param gameData represents the valuable data of the game
     */
    GameUI(GameData gameData){
        this.gameData = gameData;
        hud = new HUD(gameData);
        camera = new OrthographicCamera((Gdx.graphics.getWidth() / WIDTH_CONVERTER) / GameUI.PPM, Gdx.graphics.getHeight() / HEIGHT_CONVERTER / GameUI.PPM);
        camera.position.set(new Vector3((Gdx.graphics.getWidth()/2)/WIDTH_CONVERTER / GameUI.PPM, (Gdx.graphics.getHeight()/2)/HEIGHT_CONVERTER / GameUI.PPM, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
        super.render(delta);
        drawScenario();

        if(gameData.getGameState() == GAME) {
            checkInput();
            update();
            checkWin();
        }

        renderMap();
        drawBlock();
        drawHUD();
    }

    /**
     * function that draws the game scenario
     */
    private void drawScenario(){
        gameData.getBatch().begin();
        gameData.getBatch().draw(gameData.getCurrentLevel().getLevelScenario().getBg(), 0,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(gameData.getCurrentLevel().getLevelScenario().getScene(), 0 - infiniteScene,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().draw(gameData.getCurrentLevel().getLevelScenario().getScene(), Gdx.graphics.getWidth() - infiniteScene,0, Gdx.graphics.getWidth(), ((int) (1440*HEIGHT_CONVERTER)));
        gameData.getBatch().end();
    }

    private void drawHUD(){
        gameData.getBatch().begin();
        if(gameData.getGameState() != GAMEOVER)
            hud.draw();
        gameData.getBatch().end();
    }

    /**
     * function that renders the box2d map
     */
    private void renderMap(){
        gameData.getCurrentLevel().getLevelScenario().getMapRenderer().render();
        gameData.getCurrentLevel().getLevelScenario().getDebugRenderer().render(gameData.getCurrentLevel().getWorld(), camera.combined);
        gameData.getCurrentLevel().getBlock().update();
    }

    /**
     * function that draws the block
     */
    private void drawBlock(){
        gameData.getBatch().begin();
        gameData.getCurrentLevel().getBlock().draw(gameData.getBatch());
        gameData.getBatch().end();
    }

    /**
     * function that updates the camera and the block's movement
     */
    private void update(){
        infiniteScene = (int) (infiniteScene + 4 * WIDTH_CONVERTER) % Gdx.graphics.getWidth();

        camera.update();
        gameData.getCurrentLevel().getLevelScenario().getMapRenderer().setView(camera);
        camera.position.x += CAMERA_DELTA / PPM;
        hud.addDistance(CAMERA_DELTA);

        gameData.getCurrentLevel().getWorld().step(FPS,3,3);
        gameData.getCurrentLevel().getBlock().slide();
    }

    private void checkWin(){
        if(hud.getDistance() > gameData.getCurrentLevel().getMapDistance()) {
            gameData.setGameState(LEVELCOMPLETE);
            if(hud.getDistance() > gameData.getCurrentLevel().getMaxDistance())
                gameData.getCurrentLevel().setMaxDistance(hud.getDistance());
            gameData.setTransitioning(true);
        }
    }

    /**
     * function that checks for input in order to make the block jump
     */
    private void checkInput(){
        if((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) && !(gameData.getCurrentLevel().getBlock().getCurrentState() == Block.State.JUMPING))
            gameData.getCurrentLevel().getBlock().jump();
    }

    /**
     * function that starts a new level, setting the camera and the block to the start position
     */
    public void startLevel(){
        camera.position.set(new Vector3((Gdx.graphics.getWidth()/2)/WIDTH_CONVERTER / GameUI.PPM, (Gdx.graphics.getHeight()/2)/HEIGHT_CONVERTER / GameUI.PPM, 0));
        gameData.getCurrentLevel().getBlock().initializeBlock();
        hud.setDistance(0f);
    }

    /**
     * @return the head-up display
     */
    public HUD getHud() {
        return hud;
    }
}
