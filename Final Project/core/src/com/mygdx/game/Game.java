package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;


/**
 * main class of the application
 */
public class Game extends ApplicationAdapter {
    private GameData gameData;
    private Menu menu;
    private GameUI gameUI;
    private LevelSelector levelSelector;
    private Settings settings;
	private BlockSelector blockSelector;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create () {
	    gameData = new GameData();
	    menu = new Menu(gameData);
	    gameUI = new GameUI(gameData, 1);
	    levelSelector = new LevelSelector(gameData);
	    settings = new Settings(gameData);
		blockSelector = new BlockSelector(gameData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor( 1, 0, 0, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		switch (gameData.getGameState()) {
			case MENU:
				disableAllButtons();
			    menu.enableButtons();
				menu.render(120);
				break;
			case LEVELS:
				levelSelector.render(120);
				break;
			case GAME:
				disableAllButtons();
                gameUI.getHud().enableButtons();
				gameUI.render(120);
				break;
			case SETTINGS:
				disableAllButtons();
                settings.enableButtons();
				settings.render(120);
				break;
			case BLOCKSELECTOR:
				disableAllButtons();
				blockSelector.enableButtons();
				blockSelector.render(120);
				break;
			case LEVELSELECTOR:
				disableAllButtons();
				levelSelector.enableButtons();
				levelSelector.render(120);
				break;
			default:
				break;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();

	}

	public void disableAllButtons(){
	    menu.disableButtons();
		gameUI.getHud().disableButtons();
	    settings.disableButtons();
	    blockSelector.disableButtons();
	    levelSelector.disableButtons();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose () {
	}

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public GameUI getGameUI() {
        return gameUI;
    }

    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public LevelSelector getLevelSelector() {
        return levelSelector;
    }

    public void setLevelSelector(LevelSelector levelSelector) {
        this.levelSelector = levelSelector;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
