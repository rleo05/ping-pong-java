package com.project.game.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.project.game.PongGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setResizable(false);
        config.setForegroundFPS(60);
        config.setTitle("Pong");
        config.setWindowIcon("icon/pongicon.png");
        config.setWindowedMode(1280, 720);
        new Lwjgl3Application(new PongGame(), config);
    }
}
