package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * main class of the game character
 */
public class Block extends Actor{
    /**
     * current value of the block's height
     */
    private float y;

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

    Block(String skinPath){
        isJumping = false;
        isDropping = false;
        y = 0;
        jumpingY = 0;
        angle = 0;
        skin = new Texture(skinPath);
        skinRegion = new TextureRegion(skin);
        //this.addListener()
    }

    /**
     * function that's in charge of updating the values of y and angle, while the block is jumping
     */
    public void jump(){
        if(jumpingY < (300* Gdx.graphics.getHeight())/1080 && isJumping) {
            jumpingY += 10;
            angle = (float) (angle - 4.5) % 360;
        }

        if(jumpingY >= (300* Gdx.graphics.getHeight())/1080) {
            isDropping = true;
            isJumping = false;
        }

        if(jumpingY > 0 && isDropping) {
            jumpingY -= 10;
            angle = (float) (angle - 4.5) % 360;
        }

        if(jumpingY <=0) {
            isDropping = false;
            if(angle % 90 != 0)
                angle -= angle % 90;
        }
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
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
