package com.dirty.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dirty.juego.JumpingTOWER;

public class Render {

	public static JumpingTOWER app;
	

	public static void limpiarPantalla() //pintar� la pantalla!
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	
	
}
