package com.project.game;

import com.badlogic.gdx.Game;
import com.project.game.screens.GameScreen;
import com.project.game.screens.MenuScreen;
public class PongGame extends Game {
    public MenuScreen menuScreen;
    public GameScreen gameScreen;
    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);

        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        menuScreen.dispose();
        gameScreen.dispose();
    }
}
