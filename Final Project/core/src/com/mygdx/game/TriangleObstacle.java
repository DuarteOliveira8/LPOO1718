package com.mygdx.game;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class TriangleObstacle {
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
     * TriangleObstacle constructor
     * @param world level's world
     * @param polygon one of the level's triangle obstacle
     */
    TriangleObstacle(World world, Polygon polygon){
        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();
        createBody(world, polygon);
    }

    /**
     * creates the box2d body of the triangle obstacle
     * @param world level's world
     * @param polygon one of the level's triangle obstacle
     */
    private void createBody(World world, Polygon polygon){
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(polygon.getX() / GameUI.PPM, polygon.getY() / GameUI.PPM);

        body = world.createBody(bodyDef);

        float[] vertices = polygon.getVertices().clone();
        for (int i = 0; i < polygon.getVertices().length; i++){
            vertices[i] /= GameUI.PPM;
        }
        polygon.setVertices(vertices);
        shape.set(polygon.getVertices());
        fixtureDef.friction = 0;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("triangle");
    }
}