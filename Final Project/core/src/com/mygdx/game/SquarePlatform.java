package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;

/**
 * class that represents a platform where the block can slide or jump to
 */
public class SquarePlatform {
    /**
     * the platform box2d body
     */
    Body body;
    /**
     * the platform box2d bodyDef
     */
    BodyDef bodyDef;
    /**
     * the platform polygon shape
     */
    PolygonShape shape;
    /**
     * the platform fixture definiton
     */
    FixtureDef fixtureDef;

    /**
     * Square Platform constructor
     * @param world level's world
     * @param rectangle one of the level's platforms
     */
    SquarePlatform(World world, Rectangle rectangle){
        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        createBody(world, rectangle);
        createCollisionZone(rectangle);
    }

    /**
     * creates the box2d body of the rectangle
     * @param world the level's world
     * @param rectangle one of the level's platforms
     */
    private void createBody(World world, Rectangle rectangle){
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2) / GameUI.PPM, (rectangle.getY() + rectangle.getHeight()/2) / GameUI.PPM);
        body = world.createBody(bodyDef);

        shape.setAsBox(rectangle.getWidth()/2 / GameUI.PPM, rectangle.getHeight()/2 / GameUI.PPM);

        fixtureDef.friction = 0;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("platform");
    }

    /**
     * creates the platform collision zone (which is on its left)
     * @param rectangle one of the level's platforms
     */
    private void createCollisionZone(Rectangle rectangle){
        EdgeShape leftSide = new EdgeShape();
        leftSide.set(new Vector2(-rectangle.getWidth()/2 / GameUI.PPM, (-rectangle.getHeight()/2 + 10)/ GameUI.PPM), new Vector2(-rectangle.getWidth()/2 / GameUI.PPM, (rectangle.getHeight()/2 - 10) / GameUI.PPM));

        fixtureDef.shape = leftSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("leftSide");

        EdgeShape bottomSide = new EdgeShape();
        bottomSide.set(new Vector2(-rectangle.getWidth()/2 / GameUI.PPM, (-rectangle.getHeight()/2 - 1)/ GameUI.PPM), new Vector2(rectangle.getWidth()/2 / GameUI.PPM, (-rectangle.getHeight()/2 - 1)/ GameUI.PPM));

        fixtureDef.shape = bottomSide;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("leftSide");
    }
}
