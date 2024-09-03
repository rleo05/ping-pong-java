package com.project.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.PongGame;

public class GameScreen implements Screen {
    private final PongGame pongGame;

    public GameScreen(PongGame pongGame) {
        this.pongGame = pongGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 1, 0, 1);
    }


    @Override
    public void dispose() {

    }

    @Override
    public void resize(int i, int i1) {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
}
