package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;

public class LevelScenario {
    /**
     * background of the level
     */
    private Texture bg;
    /**
     * scene of the level
     */
    private Texture scene;
    /**
     * loads the map file
     */
    private TmxMapLoader mapLoader;
    /**
     * stores the Tiled map
     */
    private TiledMap map;
    /**
     * map renderer
     */
    private OrthogonalTiledMapRenderer mapRenderer;
    /**
     * map debug renderer
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * LevelScenario constructor
     * @param bgPath Level background texture
     * @param scenePath Level scene texture
     * @param mapPath Tiled map filepath
     */
    public LevelScenario(String bgPath, String scenePath, String mapPath){
        bg = new Texture(bgPath);
        scene = new Texture(scenePath);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapPath);
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / GameUI.PPM);
        debugRenderer = new Box2DDebugRenderer();
    }

    /**
     * @return level background
     */
    public Texture getBg() {
        return bg;
    }

    /**
     * @return level scene
     */
    public Texture getScene() {
        return scene;
    }

    /**
     * @return map renderer
     */
    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    /**
     * @return map debug renderer
     */
    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    /**
     * @return the array that stores the platforms from the corresponding map layer
     */
    public Array<RectangleMapObject> getSquarePlatformLayer(){
        return map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class);
    }

    /**
     * @return the array that stores the triangle obstacles from the corresponding map layer
     */
    public Array<PolygonMapObject> getTriangleObstacleLayer(){
        return map.getLayers().get(4).getObjects().getByType(PolygonMapObject.class);
    }

    /**
     * @return the array that stores the floor from the corresponding map layer
     */
    public Array<RectangleMapObject> getFloorLayer(){
        return map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class);
    }
}
