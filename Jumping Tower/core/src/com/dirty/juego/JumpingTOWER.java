package com.dirty.juego;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.dirty.pantallas.PantallaMenu;
import com.dirty.personajes.Batman;
import com.dirty.utiles.Render;
import com.dirty.utiles.Utiles;

public class JumpingTOWER extends Game {
	public Utiles utiles;
	public SpriteBatch sb;

	

	@Override
	public void create() {
		utiles = new Utiles();
		sb = utiles.getSb();
		Render.app = this;
	
		setScreen(new PantallaMenu(this, sb));

	}

	

	@Override
	public void render() {
		sb.begin();
		super.render();
		//batman.dibujar(sb);
		sb.end();
	}

	@Override
	public void dispose() {
		sb.dispose();

	}
}
