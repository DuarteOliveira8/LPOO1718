package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sun.org.apache.bcel.internal.generic.NEW;

import static com.mygdx.game.GameData.GameState.*;

/**
 * represents the level complete menu class
 */
public class LevelComplete extends Menu {
    /**
     * constructor of the PauseMenu class
     * @param gameData represents the valuable data of the game
     */
    LevelComplete(final GameData gameData){
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
        textures.put("levelCompleteText", new Texture("levelComplete.png"));
        textures.put("noNextLevel", new Texture("noNextLevel.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("retry", new Button((int)(741*WIDTH_CONVERTER), (int)(602*HEIGHT_CONVERTER), (int)(441*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "retry.png"));
        buttons.put("next", new Button((int)(741*WIDTH_CONVERTER), (int)(454*HEIGHT_CONVERTER), (int)(445*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER),  "next.png"));
        buttons.put("back", new Button((int) (741*WIDTH_CONVERTER), (int) (301*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton3.png"));
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
                unlockLevel();
            }
        });

        buttons.get("next").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(gameData.getCurrentLevelNo() != gameData.getLevels().size()) {
                    unlockLevel();
                    gameData.setCurrentLevelNo(gameData.getCurrentLevelNo() + 1);
                    gameData.setGameState(NEWGAME);
                    gameData.setTransitioning(true);
                }
            }
        });

        buttons.get("back").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                unlockLevel();
                gameData.setGameState(MENU);
                gameData.setTransitioning(true);
            }
        });
    }

    /**
     * unlocks the next level
     */
    public void unlockLevel(){
        if(gameData.getCurrentLevelNo() != gameData.getLevels().size())
            gameData.getLevels().get(gameData.getCurrentLevelNo()).unlockLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta){
        super.render(delta);

        gameData.getBatch().begin();
        gameData.getBatch().draw(textures.get("panel"), (int)(669*WIDTH_CONVERTER), (int)(249*HEIGHT_CONVERTER), (int)(582*WIDTH_CONVERTER), (int)(513*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("levelCompleteText"), (int)(264*WIDTH_CONVERTER), (int)(840*HEIGHT_CONVERTER), (int)(1393*WIDTH_CONVERTER), (int)(167*HEIGHT_CONVERTER));
        drawButtons();
        if(gameData.getCurrentLevelNo() == 3)
            gameData.getBatch().draw(textures.get("noNextLevel"), (int)(741*WIDTH_CONVERTER), (int)(454*HEIGHT_CONVERTER), (int)(445*WIDTH_CONVERTER), (int)(130*HEIGHT_CONVERTER));
        gameData.getBatch().end();
    }
}
