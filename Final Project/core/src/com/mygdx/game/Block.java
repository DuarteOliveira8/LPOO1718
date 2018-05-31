package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
public class Block{
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
     * boolean that determines if the block is going up
     */
    private boolean isJumping;

    /**
     * boolean that determines if the block is going down
     */
    private boolean isDropping;

    /**
     * current skin on the block
     */
    private Texture skin;

    /**
     * current skin on the block
     */
    private TextureRegion skinRegion;

    /**
     * current angle of the block
     */
    private float angle;

    private static final float JUMP_MAX_HEIGHT = 300 * Gdx.graphics.getHeight()/1080;
    private static final float JUMP_DELTA = 15 * Gdx.graphics.getHeight()/1080;
    private static final double ROTATION_DELTA = 4.5;


    private static final float WIDTH_CONVERTER = (float)(Gdx.graphics.getWidth()/1920.0);
    private static final float HEIGHT_CONVERTER = (float)(Gdx.graphics.getHeight()/1080.0);

    private World world;
    private Body body;

    Block(String skinPath, World world){
        isJumping = false;
        isDropping = false;
        y = 0;
        jumpingY = 0;
        angle = 0;
        skin = new Texture(skinPath);
        skinRegion = new TextureRegion(skin);

        this.world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(250*WIDTH_CONVERTER, 204*HEIGHT_CONVERTER);
        bodyDef.position.set(250, 204);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(100*WIDTH_CONVERTER,100*HEIGHT_CONVERTER, new Vector2((100*WIDTH_CONVERTER)/2, (100*HEIGHT_CONVERTER)/2), angle);
        shape.setAsBox(100*WIDTH_CONVERTER,100*HEIGHT_CONVERTER);
        //shape.setAsBox(100,100);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    /**
     * function that's in charge of updating the values of y and angle, while the block is jumping
     */
    public void jump(){
        if(jumpingY < JUMP_MAX_HEIGHT && isJumping) {
            jumpingY += JUMP_DELTA;
            angle = (float) (angle - ROTATION_DELTA);
        }

        if(jumpingY >= JUMP_MAX_HEIGHT) {
            isDropping = true;
            isJumping = false;
        }

        if(jumpingY > 0 && isDropping) {
            jumpingY -= JUMP_DELTA;
            angle = (float) (angle - ROTATION_DELTA);
        }

        if(jumpingY <=0) {
            isDropping = false;
            if(angle % 90 != 0)
                angle -= angle % 90;
        }
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

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isDropping() {
        return isDropping;
    }

    public void setDropping(boolean dropping) {
        isDropping = dropping;
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
}
