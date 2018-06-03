package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import static com.mygdx.game.GameData.GameState.LEVELCOMPLETE;

/**
 * class that represents each game level
 */
public class Level {
    /**
     * traveled distance
     */
    private float distance;
    /**
     * total level distance
     */
    private int mapDistance;
    /**
     * current record
     */
    private float maxDistance;
    /**
     * defines if the level is available to be played
     */
    private Touchable levelLock;
    /**
     * level's scenario
     */
    private LevelScenario levelScenario;
    /**
     * level's box2d world
     */
    private World world;
    /**
     * level's block character
     */
    private Block block;
    /**
     * game valuable data
     */
    GameData gameData;

    /**
     * Level constructor
     * @param gameData valuable game data
     * @param levelScenario the scenario of the game
     * @param mapDistance the level's total distance
     * @param isEnabled defines if the level is available to play
     */
    Level(GameData gameData, LevelScenario levelScenario, int mapDistance, Touchable isEnabled) {
        this.mapDistance = mapDistance;
        levelLock = isEnabled;
        this.levelScenario = levelScenario;
        this.gameData = gameData;
        distance = 0;

        world = new World(new Vector2(0,-9.81f), true);
        block = new Block("lightForestBlock.png", world);
        loadObstacles();
        world.setContactListener(new GameContactListener(gameData));
    }

    /**
     * loads the level's obstacles (the platforms, the triangles and the floor)
     */
    private void loadObstacles(){
        for(MapObject object : levelScenario.getSquarePlatformLayer())
            new SquarePlatform(world, ((RectangleMapObject) object).getRectangle());

        for(MapObject object : levelScenario.getTriangleObstacleLayer())
            new TriangleObstacle(world, ((PolygonMapObject) object).getPolygon());

        for(MapObject object : levelScenario.getFloorLayer())
            new SquarePlatform(world, ((RectangleMapObject) object).getRectangle());
    }

    /**
     * check if the score is greater than the max score, updating if necessary
     */
    public void checkMaxScore(){
        if(getDistance() > getMaxDistance())
            maxDistance = distance;
    }


    /**
     * check if the player has won the level
     */
    public void checkWin(){
        if(getDistance() > getMapDistance()) {
            gameData.setGameState(LEVELCOMPLETE);
            checkMaxScore();
            gameData.setTransitioning(true);
        }
    }
    /**
     * @return the max score percentage
     */
    public float getMaxPercentage(){return maxDistance/mapDistance;}

    /**
     * @return the achieved score percentage
     */
    public float getScorePercentage(){return distance/mapDistance;}

    /**
     * @return the level availability
     */
    public Touchable getLevelLock() {
        return levelLock;
    }

    /**
     * @return the level scenario
     */
    public LevelScenario getLevelScenario() {
        return levelScenario;
    }

    /**
     * @return the level's world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @return the level's block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * @return the map's distance
     */
    public int getMapDistance() {
        return mapDistance;
    }

    /**
     * @param maxDistance the new max distance
     */
    public void setMaxDistance(float maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * @return the max distance
     */
    public float getMaxDistance() {
        return maxDistance;
    }

    /**
     * unlocks a level
     */
    public void unlockLevel(){
        levelLock = Touchable.enabled;
    }

    /**
     * @param distance the new distance
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * @return current distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * adds distance
     * @param distance the distance to be added
     */
    public void addDistance(float distance){
        this.distance += distance;
    }
}