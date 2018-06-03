package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import static com.mygdx.game.GameData.GameState.*;

/**
 * class of the block selector menu
 */
public class BlockSelector extends Menu {

    /**
     * BlockSelector constructor
     * @param gameData contains the game's data
     */
    BlockSelector(final GameData gameData){
        super(gameData);
        createTextures();
        createButtons();
        createListeners();
        addActorsToStage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createTextures(){
        textures.put("blocksText", new Texture("blocks.png"));
        textures.put("blocksPanel", new Texture("blocksPanel.png"));
        textures.put("currentBlock", gameData.getCurrentLevel().getBlock().getSkin());
        textures.put("scene", new Texture("menuScene.png"));
        textures.put("background", new Texture("lightForestBG.jpg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtons(){
        buttons.put("block1", new Button((int)(499*WIDTH_CONVERTER), (int)(431*HEIGHT_CONVERTER), (int)(103*WIDTH_CONVERTER), (int)(103*HEIGHT_CONVERTER),  "lightForestBlock.png"));
        buttons.put("block2", new Button((int) (692*WIDTH_CONVERTER), (int) (431*HEIGHT_CONVERTER), (int) (103*WIDTH_CONVERTER), (int) (103*HEIGHT_CONVERTER),  "darkForestBlock.png"));
        buttons.put("block3", new Button((int) (885*WIDTH_CONVERTER), (int) (431*HEIGHT_CONVERTER), (int) (103*WIDTH_CONVERTER), (int) (103*HEIGHT_CONVERTER),  "cityBlock.png"));
        buttons.put("back", new Button((int) (1400*WIDTH_CONVERTER), (int) (17*HEIGHT_CONVERTER), (int) (441*WIDTH_CONVERTER), (int) (130*HEIGHT_CONVERTER),  "backButton1.png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createListeners(){
        buttons.get("block1").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setNewSkin("lightForestBlock.png");
                textures.put("currentBlock", gameData.getCurrentLevel().getBlock().getSkin());
            }
        });

        buttons.get("block2").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setNewSkin("darkForestBlock.png");
                textures.put("currentBlock", gameData.getCurrentLevel().getBlock().getSkin());
            }
        });

        buttons.get("block3").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                gameData.setNewSkin("cityBlock.png");
                textures.put("currentBlock", gameData.getCurrentLevel().getBlock().getSkin());
            }
        });

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
        gameData.getBatch().draw(textures.get("blocksText"), (int)(533*WIDTH_CONVERTER), (int)(877*HEIGHT_CONVERTER), (int)(860*WIDTH_CONVERTER), (int)(153*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("blocksPanel"), (int)(361*WIDTH_CONVERTER), (int)(246*HEIGHT_CONVERTER), (int)(1199*WIDTH_CONVERTER), (int)(493*HEIGHT_CONVERTER));
        gameData.getBatch().draw(textures.get("currentBlock"), (int)(1241*WIDTH_CONVERTER), (int)(392*HEIGHT_CONVERTER), (int)(222*WIDTH_CONVERTER), (int)(222*HEIGHT_CONVERTER));
        drawButtons();
        gameData.getBatch().end();
    }
}

