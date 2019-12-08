package com.tank_game.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tank_game.game.Game;
import com.tank_game.game.MainMenu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tank Game";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new Game(), config);
	}
}
