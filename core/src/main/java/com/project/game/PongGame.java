package com.project.game;

import com.badlogic.gdx.Game;
import com.project.game.screens.MenuScreen;
public class PongGame extends Game {
    public MenuScreen menuScreen;
    @Override
    public void create() {
        menuScreen = new MenuScreen(this);

        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        menuScreen.dispose();
    }
}
