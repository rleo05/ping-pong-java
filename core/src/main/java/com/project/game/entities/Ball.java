package com.project.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Ball extends Circle {
    private float velocityX = -5;
    private float velocityY = -5;

    public Ball(float x, float y, float radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void drawBall(ShapeRenderer circleShape){
        circleShape.begin(ShapeRenderer.ShapeType.Filled);
        circleShape.circle(this.x, this.y, this.radius);
        circleShape.setColor(Color.WHITE);
        circleShape.end();
    }
}
