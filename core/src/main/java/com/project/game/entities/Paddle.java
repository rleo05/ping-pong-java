package com.project.game.entities;

import com.badlogic.gdx.math.Rectangle;

public class Paddle extends Rectangle {
    public final float velocity = 10;

    public Paddle(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
