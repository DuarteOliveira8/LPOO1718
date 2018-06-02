package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GameContactListener implements ContactListener {
    private GameData gameData;

    public GameContactListener(GameData gameData){
        super();
        this.gameData = gameData;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "triangle" || fixB.getUserData() == "triangle")
            gameData.setGameState(GameData.GameState.MENU);
        else if(fixA.getUserData() == "leftSide" || fixB.getUserData() == "leftSide")
            gameData.setGameState(GameData.GameState.MENU);
        else {
            gameData.getBlock().setCurrentState(Block.State.SLIDING);
            gameData.getBlock().getBody().setAngularVelocity(0);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
