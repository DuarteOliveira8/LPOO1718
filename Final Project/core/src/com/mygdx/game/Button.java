package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Button extends Actor {

    Texture image;
    Texture content;
    TextureRegion imageRegion;

    int x;
    int y;
    int width;
    int height;

    public Button(int x, int y, int widthImage, int heightImage, String image){

        this.x = x;
        this.y = y;
        this.width = widthImage;
        this.height = heightImage;
        this.image = new Texture(image);
        imageRegion = new TextureRegion(this.image);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(imageRegion, x, y, width, height);
    }

    @Override
    public Button hit(float x, float y, boolean touchable){

        if(touchable && (getTouchable() != Touchable.enabled))
            return null;

        return (x >= this.x && x < this.x + width && y >= this.y && y < this.y + height) ? this : null;
    }
}
