package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.mygdx.game.GameData.GameState.*;

/**
 * class of the game over menu
 */
public class GameOverMenu extends Menu {
    /**
     * GameOverMenu constructor
     * @param gameData represents the valuable data of the game
     */
    GameOverMenu(final GameData gameData){
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
        textures.put("scorePanel", new Texture("scorePanel.png"));
        textures.put("gameOverText", new Texture("gameOver.png"));
        textures.put("progressBar", new Texture("progressBar.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("retry", new Button((int)(479*WIDTH_CONVERTER), (int)(23*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "retry.png"));
        buttons.put("back", new Button((int) (1002*WIDTH_CONVERTER), (int) (23*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton2.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners(){
        buttons.get("retry").addListener(new ClickListener() {
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
        gameData.getBatch().draw(textures.get("scorePanel"), (int)(669*WIDTH_CONVERTER), (int)(357*HEIGHT_CONVERTER), (int)(582*WIDTH_CONVERTER), (int)(365*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("gameOverText"), (int)(259*WIDTH_CONVERTER), (int)(817*HEIGHT_CONVERTER), (int)(1393*WIDTH_CONVERTER), (int)(156*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("progressBar"), (int)(690*WIDTH_CONVERTER), (int)(411*HEIGHT_CONVERTER), (int)(538*gameData.getCurrentLevel().getMaxPercentage()*WIDTH_CONVERTER), (int)(20*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("progressBar"), (int)(690*WIDTH_CONVERTER), (int)(570*HEIGHT_CONVERTER), (int)(538*gameData.getCurrentLevel().getScorePercentage()*WIDTH_CONVERTER), (int)(20*HEIGHT_CONVERTER));
        drawButtons();
        gameData.getBatch().end();
    }
}
