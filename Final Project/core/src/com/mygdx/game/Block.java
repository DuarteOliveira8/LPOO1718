package com.mygdx.game;

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
    float y;

    /**
     * current value of the block's jump value
     */
    float jumpingY;

    /**
     * boolean that determines if the block is going up
     */
    boolean isJumping;

    /**
     * boolean that determines if the block is going down
     */
    boolean isDropping;

    /**
     * current skin on the block
     */
    Texture skin;

    /**
     * current skin on the block
     */
    TextureRegion skinRegion;

    /**
     * current angle of the block
     */
    float angle;

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
     * changes the skin of the block
     * @param newSkinPath path of the new texture
     */
    public void changeSkin(String newSkinPath){
        skin = new Texture(newSkinPath);
    }

    /**
     * function that's in charge of updating the values of y and angle, while the block is jumping
     */
    public void jump(){
        if(jumpingY < 200 && isJumping) {
            jumpingY += 10;
            angle = (float) (angle - 4.5) % 360;
        }

        if(jumpingY >= 200) {
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
}
