package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

}
