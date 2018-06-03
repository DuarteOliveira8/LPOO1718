package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;
import static com.badlogic.gdx.scenes.scene2d.Touchable.enabled;
import static com.mygdx.game.GameData.GameState.*;

/**
 * menu where the player can learn how to play
 */
public class Tutorial extends Menu{
    /**
     * constructor of the Tutorial class
     * @param gameData represents the valuable data of the game
     */
    Tutorial(final GameData gameData){
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
        textures.put("tutorialText", new Texture("tutorial.png"));
        textures.put("instruction1", new Texture("instruction1.png"));
        textures.put("instruction2", new Texture("instruction2.png"));
        textures.put("instruction3", new Texture("instruction3.png"));
        textures.put("scene", new Texture("menuScene.png"));
        textures.put("background", new Texture("lightForestBG.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("back", new Button((int) (1400*WIDTH_CONVERTER), (int) (17*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton1.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners(){
        buttons.get("back").addListener(new ClickListener() {
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
        gameData.getBatch().draw(textures.get("background"), 0,0,Gdx.graphics.getWidth(), (int)(1440*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("scene"), 0, 0, Gdx.graphics.getWidth(), (Gdx.graphics.getHeight()*1440)/1080);
        gameData.getBatch().draw(textures.get("tutorialText"), (int)(486*WIDTH_CONVERTER), (int)(817*HEIGHT_CONVERTER), (int)(948*WIDTH_CONVERTER), (int)(217*HEIGHT_CONVERTER));
        drawButtons();
        drawInstructions();
        gameData.getBatch().end();
    }

    /**
     * draws the images that explain the game
     */
    private void drawInstructions(){
        gameData.getBatch().draw(textures.get("instruction1"), (int)(147*WIDTH_CONVERTER), (int)(308*HEIGHT_CONVERTER), (int)(410*WIDTH_CONVERTER), (int)(427*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("instruction2"), (int)(751*WIDTH_CONVERTER), (int)(308*HEIGHT_CONVERTER), (int)(410*WIDTH_CONVERTER), (int)(427*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("instruction3"), (int)(1363*WIDTH_CONVERTER), (int)(308*HEIGHT_CONVERTER), (int)(410*WIDTH_CONVERTER), (int)(427*HEIGHT_CONVERTER));
    }
    /**
     * enables the level selector buttons, if the level is unlocked
     */
    @Override
    public void enableButtons(){
        buttons.get("back").setTouchable(enabled);
    }
}
