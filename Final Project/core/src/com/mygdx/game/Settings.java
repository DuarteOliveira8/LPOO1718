package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;

/**
 * class where you can alter game settings including customizing the block
 */
public class Settings extends ScreenAdapter{
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
    private static final float PIXEL_TO_METER = (float) (16.0/ Gdx.graphics.getWidth());
    /**
     * the screen's camera
     */
    private final OrthographicCamera camera;

    private Button blocksButton;
    private Button tutorialButton;
    private Button backButton;


    private Texture settingsText;
    private Texture settingsPanel;


    private static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    private static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);


    /**
     * constructor of the Settings class
     * @param gameData represents the valuable data of the game
     */
    Settings(final GameData gameData){
        this.gameData = gameData;

        settingsPanel = new Texture("settingsPanel.png");
        settingsText = new Texture("settingsText.png");

        blocksButton = new Button((int)(740*WIDTH_CONVERTER), (int)(604*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "blocksButton.png");
        blocksButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameData.GameState.BLOCKSELECTOR);
            }
        });

        tutorialButton = new Button((int)(695*WIDTH_CONVERTER), (int)(453*HEIGHT_CONVERTER), (int)(532*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "tutorialButton.png");
        tutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameData.GameState.MENU);
            }
        });

        backButton = new Button((int)(739*WIDTH_CONVERTER), (int)(305*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "backButtonSettings.png");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameData.GameState.MENU);
            }
        });

        gameData.getSettingsStage().addActor(blocksButton);
        gameData.getSettingsStage().addActor(tutorialButton);
        gameData.getSettingsStage().addActor(backButton);

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
        gameData.getBatch().draw(gameData.levels.get(0).getBg(), 0,0,Gdx.graphics.getWidth(), 1440*HEIGHT_CONVERTER);
        gameData.getBatch().draw(gameData.menuScene, 0, 0, Gdx.graphics.getWidth(), 1440*HEIGHT_CONVERTER);
        gameData.getBatch().draw(settingsText, 453*WIDTH_CONVERTER, 815*HEIGHT_CONVERTER, 1012*WIDTH_CONVERTER, 208*HEIGHT_CONVERTER);
        gameData.getBatch().draw(settingsPanel, 670*WIDTH_CONVERTER, 251*HEIGHT_CONVERTER, 582*WIDTH_CONVERTER, 513*HEIGHT_CONVERTER);
        backButton.draw(gameData.getBatch(),0);
        tutorialButton.draw(gameData.getBatch(),0);
        blocksButton.draw(gameData.getBatch(),0);
        gameData.getBatch().end();
    }

    public void disableButtons(){
        backButton.setTouchable(disabled);
        tutorialButton.setTouchable(disabled);
        blocksButton.setTouchable(disabled);
    }

    public void enableButtons(){
        backButton.setTouchable(enabled);
        tutorialButton.setTouchable(enabled);
        blocksButton.setTouchable(enabled);
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

    public Button getBlocksButton() {
        return blocksButton;
    }

    public void setBlocksButton(Button blocksButton) {
        this.blocksButton = blocksButton;
    }

    public Button getTutorialButton() {
        return tutorialButton;
    }

    public void setTutorialButton(Button tutorialButton) {
        this.tutorialButton = tutorialButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public Texture getSettingsText() {
        return settingsText;
    }

    public void setSettingsText(Texture settingsText) {
        this.settingsText = settingsText;
    }

    public Texture getSettingsPanel() {
        return settingsPanel;
    }

    public void setSettingsPanel(Texture settingsPanel) {
        this.settingsPanel = settingsPanel;
    }
}
