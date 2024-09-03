package com.project.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.project.game.PongGame;
import com.project.game.entities.Ball;
import com.project.game.entities.Paddle;

public class GameScreen implements Screen {
    private final PongGame pongGame;

    private final float tableWidth = Gdx.graphics.getWidth();
    private final float tableHeight = Gdx.graphics.getHeight();

    private ShapeRenderer divisor;

    private SpriteBatch batch;
    private BitmapFont scoreFont;
    private int scorePlayer1;
    private int scorePlayer2;

    private Paddle player1;
    private ShapeRenderer shapePlayer1;
    private Paddle player2;
    private ShapeRenderer shapePlayer2;

    private Ball ball;
    private ShapeRenderer ballShape;

    private boolean isGameOver = false;
    private String winner;
    private BitmapFont gameOverFont;
    private BitmapFont scoreGameOverFont;

    private boolean showText = true;
    private final float blinkingTime = 0.6f;
    private float timer;

    public GameScreen(PongGame pongGame) {
        this.pongGame = pongGame;
    }

    @Override
    public void show() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        divisor = new ShapeRenderer();
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter scoreParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter.borderColor = Color.BLACK;
        scoreParameter.borderWidth = 1;
        scoreParameter.color = Color.WHITE;
        scoreParameter.size = 40;
        scoreFont = generator.generateFont(scoreParameter);

        player1 = new Paddle(20, tableHeight / 2 - 40, 15, 80);
        shapePlayer1 = new ShapeRenderer();
        player2 = new Paddle(tableWidth - 40, tableHeight / 2 - 40, 15, 80);
        shapePlayer2 = new ShapeRenderer();

        ball = new Ball(tableWidth / 2, tableHeight / 2, 15);
        ballShape = new ShapeRenderer();

        winner = "";
        FreeTypeFontGenerator.FreeTypeFontParameter parameterGameEnded = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterGameEnded.size = 20;
        parameterGameEnded.borderWidth = 1;
        parameterGameEnded.borderColor = Color.BLACK;
        parameterGameEnded.color = Color.WHITE;
        gameOverFont = generator.generateFont(parameterGameEnded);


        FreeTypeFontGenerator.FreeTypeFontParameter parameterScoreGameover = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterScoreGameover.size = 40;
        parameterScoreGameover.borderWidth = 1;
        parameterScoreGameover.borderColor = Color.BLACK;
        parameterScoreGameover.color = Color.WHITE;
        scoreGameOverFont = generator.generateFont(parameterScoreGameover);
        generator.dispose();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if(!isGameOver) {
            this.drawLineDivisor();
            this.movePaddle();
            this.ballCollisions();
            this.gameOver();

            player1.drawPaddle(shapePlayer1);
            player2.drawPaddle(shapePlayer2);

            ball.drawBall(ballShape);

            ball.x += ball.velocityX;
            ball.y += ball.velocityY;

            batch.begin();
            scoreFont.draw(batch, String.format("%02d", scorePlayer1), tableWidth / 2 - 120, tableHeight - 20);
            scoreFont.draw(batch, String.format("%02d", scorePlayer2), tableWidth / 2 + 40, tableHeight - 20);
            batch.end();
        } else {
            timer += delta;
            if(timer > blinkingTime){
                showText = !showText;
                timer = 0;
            }

            batch.begin();
            scoreGameOverFont.draw(batch, "Player 1", 100, tableHeight - 100);
            scoreGameOverFont.draw(batch, String.format("%02d", scorePlayer1), 200, tableHeight - 180);

            scoreGameOverFont.draw(batch, "Player 2", tableWidth - 430, tableHeight - 100);
            scoreGameOverFont.draw(batch, String.format("%02d", scorePlayer2), tableWidth - 320, tableHeight - 180);

            if(showText){
                gameOverFont.draw(batch, winner + " wins. Press esc to return to menu!", tableWidth / 2 - 410 , tableHeight - 550);
            }
            batch.end();
            returnToMenu();
        }
    }

    private void drawLineDivisor() {
        divisor.begin(ShapeRenderer.ShapeType.Filled);
        divisor.setColor(Color.WHITE);
        divisor.rect(tableWidth / 2 - 2, 0, 4, tableHeight);
        divisor.end();
    }

    private void movePaddle() {
        //player 1
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (player1.y < tableHeight - player1.height) {
                player1.y += player1.velocity;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (player1.y > 0) {
                player1.y -= player1.velocity;
            }
        }

        //player 2
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (player2.y < tableHeight - player2.height) {
                player2.y += player2.velocity;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (player2.y > 0) {
                player2.y -= player2.velocity;
            }
        }
    }

    private void ballCollisions() {
        //walls  - y
        if (ball.y < 15) {
            ball.velocityY = 5;
        }
        if (ball.y > tableHeight - 15) {
            ball.velocityY = -5;
        }

        //walls - x
        if (ball.x < 30) {
            scorePlayer2++;
            resetBallPosition(1);
        }
        if (ball.x > tableWidth - 30) {
            scorePlayer1++;
            resetBallPosition(-1);
        }

        //player 1 paddle
        if (isPaddleCollision(player1)) {
            ball.velocityX = 5;
        }
        //player 2 paddle
        if (isPaddleCollision(player2)) {
            ball.velocityX = -5;
        }
    }

    private void resetBallPosition(int direction) {
        ball.y = tableHeight / 2;
        ball.x = tableWidth / 2;
        ball.velocityX = 5 * direction;
        ball.velocityY = -5;
    }

    private boolean isPaddleCollision(Paddle paddle) {
        return Intersector.overlaps(ball, paddle) && ball.x > 35 && ball.x < tableWidth - 35;
    }

    private void gameOver(){
        if(scorePlayer1 >= 5 || scorePlayer2 >= 5){
            isGameOver = true;
            if(scorePlayer1 > scorePlayer2){
                winner = "Player 1";
            } else {
                winner = "Player 2";
            }
        }
    }

    private void returnToMenu(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            isGameOver = false;
            pongGame.setScreen(pongGame.menuScreen);
        }
    }

    @Override
    public void dispose() {
        divisor.dispose();
        shapePlayer1.dispose();
        shapePlayer2.dispose();
        ballShape.dispose();
        batch.dispose();
        scoreFont.dispose();
        gameOverFont.dispose();
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
