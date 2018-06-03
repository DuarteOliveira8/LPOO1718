package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.mygdx.game.GameData.GameState.*;

/**
 * menu where the player can choose what level to play
 */
public class LevelSelector extends Menu{
    /**
     * constructor of the LevelSelector class
     * @param gameData represents the valuable data of the game
     */
    LevelSelector(final GameData gameData){
        super(gameData);
        createButtons();
        createTextures();
        createListeners();
        addActorsToStage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createTextures(){
        textures.put("locked", new Texture("levelLocked.png"));
        textures.put("won", new Texture("levelWon.png"));
        textures.put("progressBar", new Texture("levelProgressBar.png"));
        textures.put("levelsText", new Texture("levels.png"));
        textures.put("scene", new Texture("menuScene.png"));
        textures.put("background", new Texture("lightForestBG.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("level1", new Button((int)(53*WIDTH_CONVERTER), (int)(290*HEIGHT_CONVERTER), (int)(561*WIDTH_CONVERTER), (int)(520*HEIGHT_CONVERTER),  "lightForestLevel.png"));
        buttons.put("level2", new Button((int)(680*WIDTH_CONVERTER), (int)(290*HEIGHT_CONVERTER), (int)(561*WIDTH_CONVERTER), (int)(520*HEIGHT_CONVERTER),  "cityLevel.png"));
        buttons.put("level3", new Button((int)(1313*WIDTH_CONVERTER), (int)(290*HEIGHT_CONVERTER), (int)(561*WIDTH_CONVERTER), (int)(520*HEIGHT_CONVERTER),  "darkForestLevel.png"));
        buttons.put("back", new Button((int) (1400*WIDTH_CONVERTER), (int) (17*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton1.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners(){
        buttons.get("level1").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setCurrentLevelNo(1);
            }
        });

        buttons.get("level2").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setCurrentLevelNo(2);
            }
        });

        buttons.get("level3").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setCurrentLevelNo(3);
            }
        });

        buttons.get("back").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(MENU);
                gameData.setTransitioning(true);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
        super.render(delta);

        gameData.getBatch().begin();
        gameData.getBatch().draw(textures.get("background"), 0,0,Gdx.graphics.getWidth(), (int)(1440*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("scene"), 0, 0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(textures.get("levelsText"), (int)(577*WIDTH_CONVERTER), (int)(880*HEIGHT_CONVERTER), (int)(768*WIDTH_CONVERTER), (int)(156*HEIGHT_CONVERTER));
        drawButtons();

        if(buttons.get("level2").getTouchable() == disabled)
            gameData.getBatch().draw(textures.get("locked"), (int)(693*WIDTH_CONVERTER), (int)(303*HEIGHT_CONVERTER), (int)(534*WIDTH_CONVERTER), (int)(393*HEIGHT_CONVERTER));


        if(buttons.get("level3").getTouchable() == disabled)
            gameData.getBatch().draw(textures.get("locked"), (int)(1325*WIDTH_CONVERTER), (int)(303*HEIGHT_CONVERTER), (int)(534*WIDTH_CONVERTER), (int)(393*HEIGHT_CONVERTER));

        gameData.getBatch().end();
    }

    /**
     * enables the level selector buttons, if the level is unlocked
     */
    @Override
    public void enableButtons(){
        buttons.get("level1").setTouchable(gameData.getLevels().get(0).getLevelLock());
        buttons.get("level2").setTouchable(gameData.getLevels().get(1).getLevelLock());
        buttons.get("level3").setTouchable(gameData.getLevels().get(2).getLevelLock());
        buttons.get("back").setTouchable(enabled);
    }
}
