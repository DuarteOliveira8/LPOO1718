package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * class that represents a different level from the game
 */
public class Level {

    /**
     * traveled distance
     */
    private int distance;
    /**
     * total distance of the level
     */
    private int mapDistance;
    /**
     * current record
     */
    private int maxDistance;

    /**
     * defines if the level is available
     */
    Touchable levelLock;

    LevelScenario levelScenario;


    Level(LevelScenario levelScenario, World world, int distance, Touchable isEnabled) {
        mapDistance = distance;
        distance = 0;
        levelLock = isEnabled;
        this.levelScenario = levelScenario;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //RECTANGLES
        for(MapObject object : levelScenario.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2) / GameUI.PPM, (rectangle.getY() + rectangle.getHeight()/2) / GameUI.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth()/2 / GameUI.PPM, rectangle.getHeight()/2 / GameUI.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //TRIANGLES
        for(MapObject object : levelScenario.getMap().getLayers().get(4).getObjects().getByType(PolygonMapObject.class))
            new TriangleObstacle(world, levelScenario.getMap(), ((PolygonMapObject) object).getPolygon());

        //FLOOR
        for(MapObject object : levelScenario.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2) / GameUI.PPM, (rectangle.getY() + rectangle.getHeight()/2) / GameUI.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth()/2 / GameUI.PPM, rectangle.getHeight()/2 / GameUI.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }


    }

    public void start(){
        distance = 0;
    }

    public int getMapDistance() {
        return mapDistance;
    }

    public void setMapDistance(int mapDistance) {
        this.mapDistance = mapDistance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Touchable getLevelLock() {
        return levelLock;
    }

    public void setLevelLock(Touchable levelLock) {
        this.levelLock = levelLock;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public LevelScenario getLevelScenario() {
        return levelScenario;
    }

    public void setLevelScenario(LevelScenario levelScenario) {
        this.levelScenario = levelScenario;
    }
}