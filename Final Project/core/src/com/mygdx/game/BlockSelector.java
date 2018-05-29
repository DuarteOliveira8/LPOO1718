package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;

/**
 * class where you can customize the block
 */
public class BlockSelector extends ScreenAdapter {
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

    private Button block1;
    private Button block2;
    private Button block3;

    private Button backButton;

    private Texture blocksText;
    private Texture blocksPanel;
    private Texture currentBlock;


    /**
     * constructor of the Settings class
     * @param gameData represents the valuable data of the game
     */
    BlockSelector(final GameData gameData){
        this.gameData = gameData;

        blocksText = new Texture("blocks.png");
        blocksPanel = new Texture("blocksPanel.png");
        currentBlock = gameData.getBlock().getSkin();

        block1 = new Button((Gdx.graphics.getWidth()*499)/1920, (Gdx.graphics.getHeight()*431)/1080, (Gdx.graphics.getWidth()*103)/1920, (Gdx.graphics.getHeight()*103)/1080,  "lightForestBlock.png");
        block1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.getBlock().setSkin(new Texture("lightForestBlock.png"));
                currentBlock = gameData.getBlock().getSkin();
                gameData.getBlock().setSkinRegion(new TextureRegion(gameData.getBlock().getSkin()));
            }
        });

        block2 = new Button((Gdx.graphics.getWidth()*692)/1920, (Gdx.graphics.getHeight()*431)/1080, (Gdx.graphics.getWidth()*103)/1920, (Gdx.graphics.getHeight()*103)/1080,  "darkForestBlock.png");
        block2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.getBlock().setSkin(new Texture("darkForestBlock.png"));
                currentBlock = gameData.getBlock().getSkin();
                gameData.getBlock().setSkinRegion(new TextureRegion(gameData.getBlock().getSkin()));
            }
        });

        block3 = new Button((Gdx.graphics.getWidth()*885)/1920, (Gdx.graphics.getHeight()*431)/1080, (Gdx.graphics.getWidth()*103)/1920, (Gdx.graphics.getHeight()*103)/1080,  "cityBlock.png");
        block3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.getBlock().setSkin(new Texture("cityBlock.png"));
                currentBlock = gameData.getBlock().getSkin();
                gameData.getBlock().setSkinRegion(new TextureRegion(gameData.getBlock().getSkin()));
            }
        });

        backButton = new Button((Gdx.graphics.getWidth()*1400)/1920, (Gdx.graphics.getHeight()*17)/1080, (Gdx.graphics.getWidth()*441)/1920, (Gdx.graphics.getHeight()*130)/1080,  "backButton.png");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameData.GameState.SETTINGS);
            }
        });

        gameData.getBlocksStage().addActor(block1);
        gameData.getBlocksStage().addActor(block2);
        gameData.getBlocksStage().addActor(block3);
        gameData.getBlocksStage().addActor(backButton);

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
        gameData.getBatch().draw(gameData.levels.get(0).getBg(), 0,0,Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(gameData.menuScene, 0, 0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(blocksText, (Gdx.graphics.getWidth()*533)/1920, (Gdx.graphics.getWidth()*877)/1920, (Gdx.graphics.getWidth()*860)/1920, (Gdx.graphics.getHeight()*153)/1080);
        gameData.getBatch().draw(blocksPanel, (Gdx.graphics.getWidth()*361)/1920, (Gdx.graphics.getWidth()*246)/1920, (Gdx.graphics.getWidth()*1199)/1920, (Gdx.graphics.getHeight()*493)/1080);
        gameData.getBatch().draw(currentBlock, (Gdx.graphics.getWidth()*1241)/1920, (Gdx.graphics.getWidth()*392)/1920, (Gdx.graphics.getWidth()*222)/1920, (Gdx.graphics.getHeight()*222)/1080);
        block1.draw(gameData.getBatch(),0);
        block2.draw(gameData.getBatch(),0);
        block3.draw(gameData.getBatch(),0);
        backButton.draw(gameData.getBatch(),0);
        gameData.getBatch().end();
    }

    public void disableButtons(){
        block1.setTouchable(disabled);
        block2.setTouchable(disabled);
        block3.setTouchable(disabled);
        backButton.setTouchable(disabled);
    }

    public void enableButtons(){
        block1.setTouchable(enabled);
        block2.setTouchable(enabled);
        block3.setTouchable(enabled);
        backButton.setTouchable(enabled);
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

    public Button getBlock1() {
        return block1;
    }

    public void setBlock1(Button block1) {
        this.block1 = block1;
    }

    public Button getBlock2() {
        return block2;
    }

    public void setBlock2(Button block2) {
        this.block2 = block2;
    }

    public Button getBlock3() {
        return block3;
    }

    public void setBlock3(Button block3) {
        this.block3 = block3;
    }

    public Texture getBlocksText() {
        return blocksText;
    }

    public void setBlocksText(Texture blocksText) {
        this.blocksText = blocksText;
    }

    public Texture getBlocksPanel() {
        return blocksPanel;
    }

    public void setBlocksPanel(Texture blocksPanel) {
        this.blocksPanel = blocksPanel;
    }

    public Texture getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Texture currentBlock) {
        this.currentBlock = currentBlock;
    }
}

