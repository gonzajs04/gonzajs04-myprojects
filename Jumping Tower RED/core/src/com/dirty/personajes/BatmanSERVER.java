package com.dirty.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.pantallas.PantallaJuegoSERVER;

public class BatmanSERVER extends PersonajeSERVER {

	private static Texture textura=new Texture("BATMAN_QUIETO.png");
	
	private float alto, ancho;
	private float x, y;
	private int puntos;
	private static boolean muerto = false;
	//private static TextureAtlas atlas = new TextureAtlas("batman.pack");
	

	
	public BatmanSERVER(World mundo,PantallaJuegoSERVER pantalla) {

		super("Batman", 0, 0, 0, 0, 0, muerto, mundo,pantalla);
		super.setVida(100);
		super.setVidaInicial(100);
		super.setEnergiaInicial(100);
		super.setEnergia(100, false);

	}
	
	


}
