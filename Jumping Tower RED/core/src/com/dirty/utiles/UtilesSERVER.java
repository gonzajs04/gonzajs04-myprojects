package com.dirty.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UtilesSERVER {

	public static boolean crearHUD = false;
    public static SpriteBatch batch = new SpriteBatch();
	public static subirCamaraEventListener scListener;
	// public static int vpHeight = Gdx.graphics.getHeight();
	// public static int vpWidth = Gdx.graphics.getWidth();
	public static int vpHeight = 720;
	public static int vpWidth = 1280;
	public static float PPM = 100;
	public static float valorUnico = 0.03f;
	public static boolean finJuego = false;
	public static String mensajeJugador1;
	public static String mensajeJugador2;
	public static AumentarEnergiaEventListenerSERVER aeListener;
	public static ReducirVelocidadEventListenerSERVER rvListener;
	public static boolean crearCronometro = false;
	public static boolean crearCamara = false;
	public static boolean cambiarZoomCam = false;
	public static boolean yaSalte=false;
	
	public static String valor;
	public static final short DEFAULT_BIT=1;	
	public static final short PERSONAJE_BIT=2;
	public static final short COIN_BIT=4;
	public static final short VIDA_BIT=8;
	public static final short DESTROYED_BIT=16;
	public SpriteBatch getSb() {
		return this.batch;
	}

}
