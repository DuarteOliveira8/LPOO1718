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
     * detects if the block is still jumping
     */
    private boolean stillJump;
    /**
     * block's box2d body
     */
    private Body body;

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
        setPosition((body.getPosition().x),(body.getPosition().y));
        body.setLinearVelocity(0,0);
        currentState = State.SLIDING;
        stillJump = false;
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
        body.setLinearVelocity((GameUI.CAMERA_DELTA * WIDTH_CONVERTER)/GameUI.PPM/GameUI.FPS,body.getLinearVelocity().y);
    }

    /**
     * function that draws the block
     * @param batch the batch where the block will be drawn
     */
    public void drawBlock(SpriteBatch batch){
        batch.begin();
        batch.draw(skinRegion, getX(), getY(), getWidth(), getHeight());
        batch.end();
    }

    /**
     * function that handles the landing of the block
     */
    public void land(){
        currentState = State.SLIDING;
        body.getPosition().x += 30;
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
        skinRegion = new TextureRegion(skin, 0, 0, 500, 500);
        setBounds(0, 0, 100*WIDTH_CONVERTER, 100*HEIGHT_CONVERTER);
        setRegion(skinRegion);
    }

    /**
     * @return current state of the block
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * @param stillJump new value
     */
    public void setStillJump(boolean stillJump) {
        this.stillJump = stillJump;
    }

    /**
     * @param currentState new State
     */
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}