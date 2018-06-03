package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * button class
 */
public class Button extends Actor {
    /**
     * button's texture image
     */
    private Texture image;
    /**
     * button's texture region
     */
    private TextureRegion imageRegion;
    /**
     * x coordinate (bottom left corner)
     */
    private int x;
    /**
     * y coordinate (bottom left corner)
     */
    private int y;
    /**
     * button image width
     */
    private int width;
    /**
     * button image height
     */
    private int height;

    /**
     * Button constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param widthImage button image width
     * @param heightImage button image height
     * @param image button image filepath
     */
    public Button(int x, int y, int widthImage, int heightImage, String image){
        this.x = x;
        this.y = y;
        this.width = widthImage;
        this.height = heightImage;
        this.image = new Texture(image);
        imageRegion = new TextureRegion(this.image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(imageRegion, x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button hit(float x, float y, boolean touchable) {
        if (touchable && (getTouchable() != Touchable.enabled))
            return null;

        return (x >= this.x && x < this.x + width && y >= this.y && y < this.y + height) ? this : null;
    }

    /**
     * {@inheritDoc}
     */
   @Override
   public float getX(){
        return x;
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getY(){
        return y;
    }
}
