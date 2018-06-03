package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.mygdx.game.GameData.GameState.*;

/**
 * class of the main Menu
 */
public class MainMenu extends Menu{
    /**
     * Menu constructor
     * @param gameData represents the valuable data of the game
     */
    MainMenu(final GameData gameData){
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
    protected void createButtons() {
        buttons.put("play", new Button((int)(838*WIDTH_CONVERTER), (int)(280*HEIGHT_CONVERTER), (int)(245*WIDTH_CONVERTER), (int)(258*HEIGHT_CONVERTER),  "play.png"));
        buttons.put("exit", new Button((int)(1400*WIDTH_CONVERTER), (int)(25*HEIGHT_CONVERTER), (int)(442*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "exitbutton.png"));
        buttons.put("level", new Button((int)(499*WIDTH_CONVERTER), (int)(300*HEIGHT_CONVERTER), (int)(204*WIDTH_CONVERTER), (int)(215*HEIGHT_CONVERTER),  "level.png"));
        buttons.put("settings", new Button((int)(1218*WIDTH_CONVERTER), (int)(300*HEIGHT_CONVERTER), (int)(204*WIDTH_CONVERTER), (int)(215*HEIGHT_CONVERTER),  "settings.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createTextures() {
        textures.put("logo", new Texture("logo.png"));
        textures.put("scene", new Texture("menuScene.png"));
        textures.put("background", new Texture("lightForestBG.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners() {
        buttons.get("play").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(NEWGAME);
                gameData.setTransitioning(true);
            }
        });

        buttons.get("exit").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        buttons.get("level").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(LEVELSELECTOR);
                gameData.setTransitioning(true);
            }
        });

        buttons.get("settings").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(SETTINGS);
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
        gameData.getBatch().draw(textures.get("logo"), 420*WIDTH_CONVERTER, 737*HEIGHT_CONVERTER, 1074*WIDTH_CONVERTER, 218*HEIGHT_CONVERTER);
        drawButtons();
        gameData.getBatch().end();
    }
}
