package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.mygdx.game.GameData.GameState.*;

/**
 * class to edit the game settings
 */
public class Settings extends Menu{
    /**
     * constructor of the Settings class
     * @param gameData represents the valuable data of the game
     */
    Settings(final GameData gameData){
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
        textures.put("settingsPanel", new Texture("panel.png"));
        textures.put("settingsText", new Texture("settingsText.png"));
        textures.put("scene", new Texture("menuScene.png"));
        textures.put("background", new Texture("lightForestBG.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("blocks", new Button((int)(740*WIDTH_CONVERTER), (int)(604*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "blocksButton.png"));
        buttons.put("tutorial", new Button((int)(695*WIDTH_CONVERTER), (int)(453*HEIGHT_CONVERTER), (int)(532*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "tutorialButton.png"));
        buttons.put("back", new Button((int)(739*WIDTH_CONVERTER), (int)(305*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "backButtonSettings.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners(){
        buttons.get("blocks").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(BLOCKSELECTOR);
                gameData.setTransitioning(true);
            }
        });

        buttons.get("tutorial").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(MENU);
                gameData.setTransitioning(true);
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
        gameData.getBatch().draw(textures.get("background"), 0,0,Gdx.graphics.getWidth(), 1440*HEIGHT_CONVERTER);
        gameData.getBatch().draw(textures.get("scene"), 0, 0, Gdx.graphics.getWidth(), 1440*HEIGHT_CONVERTER);
        gameData.getBatch().draw(textures.get("settingsText"), 453*WIDTH_CONVERTER, 815*HEIGHT_CONVERTER, 1012*WIDTH_CONVERTER, 208*HEIGHT_CONVERTER);
        gameData.getBatch().draw(textures.get("settingsPanel"), 670*WIDTH_CONVERTER, 251*HEIGHT_CONVERTER, 582*WIDTH_CONVERTER, 513*HEIGHT_CONVERTER);
        drawButtons();
        gameData.getBatch().end();
    }
}
