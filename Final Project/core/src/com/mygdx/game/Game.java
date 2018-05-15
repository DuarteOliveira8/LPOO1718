package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

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
		gameUI.render(60);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose () {
	}
}
