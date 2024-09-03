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
import com.project.game.entities.Paddle;

public class GameScreen implements Screen {
    private final PongGame pongGame;

    private ShapeRenderer divisor;

    private SpriteBatch batch;
    private BitmapFont scoreFont;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;

    private Paddle player1;
    private ShapeRenderer shapePlayer1;
    private Paddle player2;
    private ShapeRenderer shapePlayer2;

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

        player1 = new Paddle(20, Gdx.graphics.getHeight() / 2 - 40, 15, 80);
        shapePlayer1 = new ShapeRenderer();
        player2 = new Paddle(Gdx.graphics.getWidth() - 40, Gdx.graphics.getHeight()/ 2 - 40, 15, 80);
        shapePlayer2 = new ShapeRenderer();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        drawLineDivisor();

        player1.drawPaddle(shapePlayer1);
        player2.drawPaddle(shapePlayer2);

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
        shapePlayer1.dispose();
        shapePlayer2.dispose();
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
