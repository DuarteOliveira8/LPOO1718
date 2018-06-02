package com.mygdx.game;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;

public class SquarePlatform {
    World world;
    TiledMap map;
    TiledMapTile tile;
    Rectangle rectangle;
    Body body;

    SquarePlatform(World world, TiledMap map, Rectangle rectangle){
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        this.rectangle = rectangle;

        fixtureDef.friction = 0;

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2) / GameUI.PPM, (rectangle.getY() + rectangle.getHeight()/2) / GameUI.PPM);

        body = world.createBody(bodyDef);

        shape.setAsBox(rectangle.getWidth()/2 / GameUI.PPM, rectangle.getHeight()/2 / GameUI.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("platform");

        EdgeShape leftSide = new EdgeShape();
        leftSide.set(new Vector2(-rectangle.getWidth()/2 / GameUI.PPM, (-rectangle.getHeight()/2 + 10)/ GameUI.PPM), new Vector2(-rectangle.getWidth()/2 / GameUI.PPM, (rectangle.getHeight()/2 - 10) / GameUI.PPM));
        fixtureDef.shape = leftSide;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData("leftSide");
    }
}
