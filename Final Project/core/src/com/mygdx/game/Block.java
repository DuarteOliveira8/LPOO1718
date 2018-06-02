package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * main class of the game character
 */
public class Block extends Sprite {
    public enum State {DROPPING, JUMPING, SLIDING}

    /**
     * current value of the block's height
     */
    private float y;

    /**
     * current value of the block's height
     */
    private float x;

    /**
     * current value of the block's jump value
     */
    private float jumpingY;

    /**
     * current skin on the block
     */
    private Texture skin;

    /**
     * current skin on the block
     */
    private TextureRegion skinRegion;

    /**
     * current state of the block
     */
    State currentState;

    /**
     * current angle of the block
     */
    private float angle;

    private static final float JUMP_MAX_HEIGHT = 300 * Gdx.graphics.getHeight()/1080;
    private static final float JUMP_DELTA = 15 * Gdx.graphics.getHeight()/1080;
    private static final double ROTATION_DELTA = 4.5;


    private static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    private static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);


    /**
     * allows us to convert from pixels to meters
     */
    private static final float PIXEL_TO_METER = (float) (16.0/Gdx.graphics.getWidth());

    private World world;
    private Body body;

    Block(String skinPath, World world){
        currentState = State.SLIDING;
        y = 0;
        jumpingY = 0;
        angle = 0;
        skin = new Texture(skinPath);
        skinRegion = new TextureRegion(skin, 0, 0, 500, 500);

        setBounds(0, 0, 100*WIDTH_CONVERTER, 100*HEIGHT_CONVERTER);
        setRegion(skinRegion);

        this.world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((250 + 50) / GameUI.PPM, (204 + 50) / GameUI.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50 / GameUI.PPM,50 / GameUI.PPM);


        fixtureDef.friction = 0;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("block");
    }

    /**
     * function that's in charge of updating the values of y and angle, while the block is jumping
     */
    public void jump(){
        if(currentState == State.SLIDING) {
            body.setLinearVelocity(GameUI.CAMERA_DELTA / GameUI.PPM / GameUI.FPS, 3f);
            currentState = State.JUMPING;
        }
    }

    public void update(){
        setPosition((0.6f*GameUI.PPM)*WIDTH_CONVERTER - getWidth()/2, body.getPosition().y*GameUI.PPM*HEIGHT_CONVERTER - getHeight()/2);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getJumpingY() {
        return jumpingY;
    }

    public void setJumpingY(float jumpingY) {
        this.jumpingY = jumpingY;
    }

    public Texture getSkin() {
        return skin;
    }

    public void setSkin(Texture skin) {
        this.skin = skin;
    }

    public TextureRegion getSkinRegion() {
        return skinRegion;
    }

    public void setSkinRegion(TextureRegion skinRegion) {
        this.skinRegion = skinRegion;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public static float getJumpMaxHeight() {
        return JUMP_MAX_HEIGHT;
    }

    public static float getJumpDelta() {
        return JUMP_DELTA;
    }

    public static double getRotationDelta() {
        return ROTATION_DELTA;
    }

    public static float getWidthConverter() {
        return WIDTH_CONVERTER;
    }

    public static float getHeightConverter() {
        return HEIGHT_CONVERTER;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
