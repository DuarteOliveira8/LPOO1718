package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import static com.mygdx.game.Menu.HEIGHT_CONVERTER;
import static com.mygdx.game.Menu.WIDTH_CONVERTER;

/**
 * class of the game character
 */
public class Block extends Sprite {
    /**
     * enum types that allows to check if the block is sliding on the floor/platforms, or jumping
     */
    public enum State {JUMPING, SLIDING}
    /**
     * current texture of the block
     */
    private Texture skin;
    /**
     * current texture region of the block
     */
    private TextureRegion skinRegion;
    /**
     * current state of the block
     */
    private State currentState;
    /**
     * block's box2d body
     */
    private Body body;
    /**
     * block's sprite angle
     */
    private float angle;

    /**
     * Block constructor
     * @param skinPath path to the skin's texture file
     * @param world current level world
     */
    Block(String skinPath, World world){
        currentState = State.SLIDING;
        skin = new Texture(skinPath);
        skinRegion = new TextureRegion(skin, 0, 0, 500, 500);
        setBounds(0, 0, 100*WIDTH_CONVERTER, 100*HEIGHT_CONVERTER);
        setRegion(skinRegion);

        createBody(world);
    }

    /**
     * creates the block's box2d body
     * @param world current level world
     */
    private void createBody(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((250 + 50) / GameUI.PPM, (204 + 50) / GameUI.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50 / GameUI.PPM,50 / GameUI.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef).setUserData("block");
    }


    /**
     * initializes the block's position and velocity whenever a new level starts
     */
    public void initializeBlock(){
        body.setTransform((250 + 50) / GameUI.PPM, (204 + 50) / GameUI.PPM, 0);
        body.setLinearVelocity(0,0);
        angle = 0;
        currentState = State.SLIDING;
    }

    /**
     * function that applies a linear velocity to the block to make it jump
     */
    public void jump(){
        if(currentState == State.SLIDING) {
            body.setLinearVelocity((GameUI.CAMERA_DELTA * WIDTH_CONVERTER) / GameUI.PPM / GameUI.FPS, 3f);
            currentState = State.JUMPING;
        }
    }

    public void move(){
        if (currentState == State.JUMPING) {
            body.setTransform(body.getPosition(), body.getAngle() + (float) (-4.5 * Math.PI) / 180);
            angle -= 4.5;
        }
        else {
            body.setTransform(body.getPosition(), 0);
            angle = 0;
        }

        body.setLinearVelocity((GameUI.CAMERA_DELTA * WIDTH_CONVERTER)/GameUI.PPM/GameUI.FPS,body.getLinearVelocity().y);
    }

    /**
     * function that draws the block
     * @param batch the batch where the block will be drawn
     */
    public void drawBlock(SpriteBatch batch){
        batch.begin();

        if(currentState == State.SLIDING)
            batch.draw(skinRegion, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, 0);
        else
            batch.draw(skinRegion, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, angle);

        batch.end();
    }

    /**
     * function that handles the landing of the block
     */
    public void land(){
        currentState = State.SLIDING;
        body.setAngularVelocity(0);
    }

    /**
     * updates the body's sprite position according to its box2d body movement
     */
    public void update(){
        setPosition((0.6f*GameUI.PPM)*WIDTH_CONVERTER - getWidth()/2, body.getPosition().y*GameUI.PPM*HEIGHT_CONVERTER - getHeight()/2);

    }

    /**
     * @return block's texture
     */
    public Texture getSkin() {
        return skin;
    }

    /**
     * @param skin sets a new skin
     */
    public void setSkin(Texture skin) {
        this.skin = skin;
    }

    /**
     * @return current state of the block
     */
    public State getCurrentState() {
        return currentState;
    }
}