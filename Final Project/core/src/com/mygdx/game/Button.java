package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Button extends Actor {

    private Texture image;
    private Texture content;
    private TextureRegion imageRegion;

    private int x;
    private int y;
    private int width;
    private int height;

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

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Texture getContent() {
        return content;
    }

    public void setContent(Texture content) {
        this.content = content;
    }

    public TextureRegion getImageRegion() {
        return imageRegion;
    }

    public void setImageRegion(TextureRegion imageRegion) {
        this.imageRegion = imageRegion;
    }

    @Override
    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
