package com.dirty.jumpingtower.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dirty.juego.JumpingTOWER;


public class DesktopLauncher {
	public static void main(String[] arg) {
	
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		
		
		
		new LwjglApplication(new JumpingTOWER(), config);
		config.vSyncEnabled = true;
		config.width = 1280;
		config.height = 720;
		Gdx.graphics.setResizable(true);
	}
}

