package com.mygdx.game;

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
    int widthImage;
    int heightImage;
    int widthContent;
    int heightContent;

    public Button(int x, int y, int widthImage, int heightImage, int widthContent, int heightContent, String image, String content){
        this.x = x;
        this.y = y;
        this.widthImage = widthImage;
        this.heightImage = heightImage;
        this.widthContent = widthContent;
        this.heightContent = heightContent;
        this.image = new Texture(image);
        this.content = new Texture(content);
        imageRegion = new TextureRegion(this.image);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(imageRegion, x, y, widthImage, heightImage);
        batch.draw(content, x + widthImage/2 - widthContent/2, y + heightImage/2 - heightContent/2, widthContent, heightContent);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable){
        if(touchable && getTouchable() != Touchable.enabled)
            return null;

        return x >= 0 && x < widthImage && y >= 0 && y < heightImage ? this : null;
    }
}
