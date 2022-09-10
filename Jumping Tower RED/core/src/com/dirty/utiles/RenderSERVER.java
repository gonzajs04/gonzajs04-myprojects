package com.dirty.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.dirty.juego.JumpingTOWERRED;


public class RenderSERVER {

	public static JumpingTOWERRED app;
	

	public static void limpiarPantalla() //pintará la pantalla!
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	
	
}
