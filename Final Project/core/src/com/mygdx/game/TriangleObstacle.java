package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.Rectangle;

public class TriangleObstacle {
    World world;
    TiledMap map;
    TiledMapTile mapTile;
    Polygon polygon;
    Body body;


    public TriangleObstacle(World world, TiledMap map, Polygon polygon){
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        this.polygon = polygon;

        fixtureDef.friction = 0;

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(polygon.getX() / GameUI.PPM, polygon.getY() / GameUI.PPM);

        body = world.createBody(bodyDef);

        float[] vertices = polygon.getVertices().clone();
        for (int i = 0; i < polygon.getVertices().length; i++){
            vertices[i] /= GameUI.PPM;
        }
        polygon.setVertices(vertices);

        shape.set(polygon.getVertices());
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("triangle");

    }
}
