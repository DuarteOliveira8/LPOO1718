package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.mygdx.game.GameData.GameState.*;

/**
 * represents the pause menu class
 */
public class PauseMenu extends Menu {
    /**
     * constructor of the PauseMenu class
     * @param gameData represents the valuable data of the game
     */
    PauseMenu(final GameData gameData){
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
        textures.put("panel", new Texture("panel.png"));
        textures.put("pausedText", new Texture("paused.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("resume", new Button((int)(741*WIDTH_CONVERTER), (int)(602*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "resume.png"));
        buttons.put("restart", new Button((int)(694*WIDTH_CONVERTER), (int)(454*HEIGHT_CONVERTER), (int)(532*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "restart.png"));
        buttons.put("back", new Button((int) (740*WIDTH_CONVERTER), (int) (301*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton3.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners(){
        buttons.get("resume").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(GAME);
                gameData.setTransitioning(true);
            }
        });

        buttons.get("restart").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setGameState(NEWGAME);
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
        gameData.getBatch().draw(textures.get("pausedText"), (int)(482*WIDTH_CONVERTER), (int)(818*HEIGHT_CONVERTER), (int)(951*WIDTH_CONVERTER), (int)(217*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("panel"), (int)(671*WIDTH_CONVERTER), (int)(249*HEIGHT_CONVERTER), (int)(582*WIDTH_CONVERTER), (int)(513*HEIGHT_CONVERTER));
        drawButtons();
        gameData.getBatch().end();
    }
}
