package com.project.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.PongGame;

public class GameScreen implements Screen {
    private final PongGame pongGame;

    private ShapeRenderer divisor;

    private SpriteBatch batch;
    private BitmapFont scoreFont;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    public GameScreen(PongGame pongGame) {
        this.pongGame = pongGame;
    }

    @Override
    public void show() {
        divisor = new ShapeRenderer();
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter scoreParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter.borderColor = Color.BLACK;
        scoreParameter.borderWidth = 1;
        scoreParameter.color = Color.WHITE;
        scoreParameter.size = 40;
        scoreFont = generator.generateFont(scoreParameter);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        drawLineDivisor();

        batch.begin();
        scoreFont.draw(batch, String.format("%02d", scorePlayer1), Gdx.graphics.getWidth()/2 - 120, Gdx.graphics.getHeight() - 20);
        scoreFont.draw(batch, String.format("%02d", scorePlayer2), Gdx.graphics.getWidth()/2 + 40, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    private void drawLineDivisor() {
        divisor.begin(ShapeRenderer.ShapeType.Filled);
        divisor.setColor(Color.WHITE);
        divisor.rect(Gdx.graphics.getWidth() / 2 - 2, 0, 4, Gdx.graphics.getHeight() );
        divisor.end();
    }


    @Override
    public void dispose() {
        divisor.dispose();
        batch.dispose();
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
