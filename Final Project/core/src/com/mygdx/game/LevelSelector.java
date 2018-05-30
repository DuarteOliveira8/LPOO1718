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
 * menu where the player can choose what level to play
 */
public class LevelSelector extends ScreenAdapter{
    /**
     * represents the valuable data of the game
     */
    private GameData gameData;

    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private Button backButton;


    private Texture locked;
    private Texture won;
    private Texture progressBar;
    private Texture levelsText;

    private static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    private static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);

    /**
     * constructor of the LevelSelector class
     * @param gameData represents the valuable data of the game
     */
    LevelSelector(final GameData gameData){
        this.gameData = gameData;

        locked = new Texture("levelLocked.png");
        won = new Texture("levelWon.png");
        progressBar = new Texture("levelProgressBar.png");
        levelsText = new Texture("levels.png");

        level1Button = new Button((int)(53*WIDTH_CONVERTER), (int)(290*HEIGHT_CONVERTER), (int)(561*WIDTH_CONVERTER), (int)(520*HEIGHT_CONVERTER),  "lightForestLevel.png");
        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setCurrentLevelNo(1);
            }
        });

        level2Button = new Button((int)(680*WIDTH_CONVERTER), (int)(290*HEIGHT_CONVERTER), (int)(561*WIDTH_CONVERTER), (int)(520*HEIGHT_CONVERTER),  "cityLevel.png");
        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setCurrentLevelNo(2);
            }
        });

        level3Button = new Button((int)(1313*WIDTH_CONVERTER), (int)(290*HEIGHT_CONVERTER), (int)(561*WIDTH_CONVERTER), (int)(520*HEIGHT_CONVERTER),  "darkForestLevel.png");
        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setCurrentLevelNo(3);
            }
        });

        backButton = new Button((int) (1400*WIDTH_CONVERTER), (int) (17*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton.png");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GameData.GameState.SETTINGS);
            }
        });

        gameData.getLevelSelectorStage().addActor(level1Button);
        gameData.getLevelSelectorStage().addActor(level2Button);
        gameData.getLevelSelectorStage().addActor(level3Button);
        gameData.getLevelSelectorStage().addActor(backButton);
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

        gameData.getBatch().begin();
        gameData.getBatch().draw(gameData.getLevels().get(0).getBg(), 0,0,Gdx.graphics.getWidth(), (int)(1440*HEIGHT_CONVERTER));
        gameData.getBatch().draw(gameData.getMenuScene(), 0, 0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(levelsText, (int)(577*WIDTH_CONVERTER), (int)(880*HEIGHT_CONVERTER), (int)(768*WIDTH_CONVERTER), (int)(156*HEIGHT_CONVERTER));
        level1Button.draw(gameData.getBatch(),0);
        level2Button.draw(gameData.getBatch(),0);
        level3Button.draw(gameData.getBatch(),0);
        backButton.draw(gameData.getBatch(),0);
        gameData.getBatch().end();
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

    public void disableButtons(){
        level1Button.setTouchable(disabled);
        level2Button.setTouchable(disabled);
        level3Button.setTouchable(disabled);
        backButton.setTouchable(disabled);
    }

    public void enableButtons(){
        level1Button.setTouchable(gameData.getLevels().get(0).getLevelLock());
        level2Button.setTouchable(gameData.getLevels().get(1).getLevelLock());
        level3Button.setTouchable(gameData.getLevels().get(2).getLevelLock());
        backButton.setTouchable(enabled);
    }
}
