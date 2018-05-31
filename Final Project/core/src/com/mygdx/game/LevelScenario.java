package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

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
     * scene of the level
     */
    private Texture floor;


    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    Box2DDebugRenderer debugRenderer;

    public LevelScenario(String bgPath, String scenePath, String floorPath){
        bg = new Texture(bgPath);
        scene = new Texture(scenePath);
        floor = new Texture(floorPath);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("teste1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        debugRenderer = new Box2DDebugRenderer();
    }

    public Texture getBg() {
        return bg;
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }

    public Texture getScene() {
        return scene;
    }

    public void setScene(Texture scene) {
        this.scene = scene;
    }

    public Texture getFloor() {
        return floor;
    }

    public void setFloor(Texture floor) {
        this.floor = floor;
    }

    public TmxMapLoader getMapLoader() {
        return mapLoader;
    }

    public void setMapLoader(TmxMapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    public void setMapRenderer(OrthogonalTiledMapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public void setDebugRenderer(Box2DDebugRenderer debugRenderer) {
        this.debugRenderer = debugRenderer;
    }
}
