package com.dirty.camara;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dirty.juego.JumpingTOWER;
import com.dirty.personajes.Personaje;
import com.dirty.utiles.Utiles;

import configuraciones.Configuraciones;

public class Camara {

	private OrthographicCamera camaraJuego;
	private Viewport gamePort;
	

	public Camara()
	{
		

		// Ubica la camara a donde esta nuestro Player
		camaraJuego = new OrthographicCamera();
		// Hace la ventana deljuego se adapte a la resolucion quequeremos,
		gamePort = new FitViewport(Utiles.vpWidth / Utiles.PPM, Utiles.vpHeight /Utiles.PPM, camaraJuego);
		camaraJuego.position.set(gamePort.getScreenWidth()/2,gamePort.getScreenHeight()/2, 0);
		// la posicion donde comienza el mapa es en 130, pero desde la cam el 400
		
	}

	public float getAnchoViewportX() {
		return this.gamePort.getScreenX();
	}
	
	public float getAnchoViewportY() {
		return this.gamePort.getScreenY();
	}
	


	public OrthographicCamera getCamaraJuego() {
		return this.camaraJuego;
	}

	public Viewport getGamePort() {
		return this.gamePort;
	}

	

}
