package com.dirty.camara;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dirty.utiles.UtilesSERVER;

public class CamaraSERVER {

	private OrthographicCamera camaraJuego;
	private Viewport gamePort;
	private float posicionOriginalY;
	private float posicionOriginalX;
	private float posicionMaximaX;
	private float posicionMaximaY = 7233;

	public CamaraSERVER() {

		// Ubica la camara a donde esta nuestro Player
		camaraJuego = new OrthographicCamera();
		// Hace la ventana deljuego se adapte a la resolucion quequeremos,
		gamePort = new FitViewport(UtilesSERVER.vpWidth / UtilesSERVER.PPM, UtilesSERVER.vpHeight / UtilesSERVER.PPM,
				camaraJuego);
		camaraJuego.position.set(gamePort.getScreenWidth()/2, gamePort.getScreenHeight()/2, 0);
		// la posicion donde comienza el mapa es en 130, pero desde la cam el 400
		posicionOriginalY = 447;
		posicionOriginalX = 1435;
		posicionMaximaX = 2500;
	}
	
	public float getAnchoViewportX() {
		return this.gamePort.getScreenX();
	}
	
	public float getAnchoViewportY() {
		return this.gamePort.getScreenY();
	}

	public float getPosicionOriginalY() {
		return this.posicionOriginalY;
	}

	public float getPosicionOriginalX() {
		return this.posicionOriginalX;
	}

	public OrthographicCamera getCamaraJuego() {
		return this.camaraJuego;
	}

	public Viewport getGamePort() {
		return this.gamePort;
	}

	public float getPosicionMaximaX() {
		return this.posicionMaximaX;
	}

	public float getPosicionMaximaY() {
		return this.posicionMaximaY;
	}

}
