package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.GameData.GameState;
import java.util.Map;
import java.util.TreeMap;
import static com.mygdx.game.GameData.GameState.*;


/**
 * main class of the application
 */
public class Game extends ApplicationAdapter {
	/**
	 * contains the game's data
	 */
    private GameData gameData;
	/**
	 * the game UI
	 */
    private GameUI gameUI;
	/**
	 * array that stores all the program's menus
	 */
	protected Map<GameState,Menu> menus;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create () {
	    gameData = new GameData();
	    gameUI = new GameUI(gameData);
	    menus = new TreeMap<GameState, Menu>();
	    loadMenus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render () {
		checkTransition();

		if(gameData.getGameState() == GAME)
			renderGame();
		else if(gameData.getGameState() == NEWGAME)
			newGame();
		else
			renderMenus();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
	}

	/**
	 * function that loads all the menus and stores them into the array
	 */
	private void loadMenus(){
		menus.put(MENU, new MainMenu(gameData));
		menus.put(LEVELSELECTOR, new LevelSelector(gameData));
		menus.put(SETTINGS, new Settings(gameData));
		menus.put(BLOCKSELECTOR, new BlockSelector(gameData));
		menus.put(PAUSED, new PauseMenu(gameData));
		menus.put(GAMEOVER, new GameOverMenu(gameData));
		menus.put(LEVELCOMPLETE, new LevelComplete(gameData));
	}

	/**
	 * function that checks when a transition from one menu to another is ocurring, disabling all the buttons and enabling the ones from the new menu
	 */
	private void checkTransition(){
		if(gameData.isTransitioning()) {
			disableAllButtons();
			if(gameData.getGameState() == NEWGAME || gameData.getGameState() == GAME)
				gameUI.getHud().enableButtons();
			else if (gameData.getGameState() != NEWGAME)
				menus.get(gameData.getGameState()).enableButtons();
			gameData.setTransitioning(false);
		}
	}

	/**
	 * function that renders the game UI
	 */
	private void renderGame(){
		gameUI.render(60);
	}

	/**
	 * function that renders the menus
	 */
	private void renderMenus(){
		if(gameData.getGameState() == PAUSED || gameData.getGameState() == GAMEOVER || gameData.getGameState() == LEVELCOMPLETE)
			renderGame();
		menus.get(gameData.getGameState()).enableButtons();
		menus.get(gameData.getGameState()).render(60);
	}

	/**
	 * function that starts a new Game
	 */
	private void newGame(){
		gameUI.startLevel();
		gameData.setGameState(GAME);
	}

	/**
	 * function that disables all the menu's buttons
	 */
	private void disableAllButtons(){
		gameUI.getHud().disableButtons();
		for(Map.Entry<GameState, Menu> menu : menus.entrySet())
			menu.getValue().disableButtons();
    }
}
