package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * main class of the game character
 */
public class Block extends Actor{
    /**
     * current value of the block's height
     */
    int y = 0;

    /**
     * current skin on the block
     */
    Texture skin = new Texture("block_desert.png");

    /**
     * current angle of the block
     */
    float angle = 0;

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
        //TODO
    }
}
