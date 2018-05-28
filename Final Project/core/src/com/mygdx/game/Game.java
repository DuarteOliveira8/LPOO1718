package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


/**
 * main class of the application
 */
public class Game extends ApplicationAdapter {
	GameData gameData;
	Menu menu;
	GameUI gameUI;
	HUD hud;
	LevelSelector levelSelector;
	Settings settings;

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

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render () {
		switch (gameData.gameState) {
			case MENU:
				menu.render(120);
				break;
			case LEVELS:
				levelSelector.render(120);
				break;
			case GAME:
				gameUI.render(120);
				break;
			case SETTINGS:
				settings.render(120);
				break;
			default:
				break;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose () {
	}
}
