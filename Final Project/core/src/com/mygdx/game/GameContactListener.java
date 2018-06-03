package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import static com.mygdx.game.GameData.GameState.*;

/**
 * class used for game collisions
 */
public class GameContactListener implements ContactListener {
    /**
     * contains the game's data
     */
    private GameData gameData;

    /**
     * GameContactListener consrtuctor
     * @param gameData game's data
     */
    public GameContactListener(GameData gameData){
        super();
        this.gameData = gameData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getUserData() == "triangle" || contact.getFixtureB().getUserData() == "triangle" || contact.getFixtureA().getUserData() == "leftSide" || contact.getFixtureB().getUserData() == "leftSide") {
            gameData.setGameState(GAMEOVER);
            gameData.getCurrentLevel().checkMaxScore();
            Gdx.input.vibrate(100);
            gameData.setTransitioning(true);
        }
        else {
            //TODO
            if ((Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)))
                gameData.getCurrentLevel().getBlock().setStillJump(true);
            else
                gameData.getCurrentLevel().getBlock().setStillJump(false);

            gameData.getCurrentLevel().getBlock().land();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void endContact(Contact contact) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { }
}
