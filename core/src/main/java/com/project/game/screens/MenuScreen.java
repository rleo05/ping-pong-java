package com.project.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.PongGame;

public class MenuScreen implements Screen {
    private final PongGame pongGame;

    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont textFont;

    public MenuScreen(PongGame pongGame) {
        this.pongGame = pongGame;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParameter.size = 100;
        titleParameter.color = Color.WHITE;
        titleParameter.borderWidth = 1;
        titleParameter.borderColor = Color.BLACK;
        titleFont = generator.generateFont(titleParameter);

        FreeTypeFontGenerator.FreeTypeFontParameter textParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        textParameter.size = 20;
        textParameter.color = Color.WHITE;
        textParameter.borderWidth = 1;
        textParameter.borderColor = Color.BLACK;
        textFont = generator.generateFont(textParameter);

        generator.dispose();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        titleFont.draw(batch, "Pong", Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() - 200);
        textFont.draw(batch, "Press space to start the game", Gdx.graphics.getWidth() / 2 - 320, Gdx.graphics.getHeight() - 550);
        batch.end();
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
