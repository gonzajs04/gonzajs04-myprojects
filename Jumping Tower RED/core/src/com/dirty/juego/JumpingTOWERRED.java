package com.dirty.juego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dirty.pantallas.PantallaFinSERVER;
import com.dirty.pantallas.PantallaJuegoSERVER;
import com.dirty.pantallas.PantallaMenuSERVER;
import com.dirty.personajes.BatmanSERVER;
import com.dirty.personajes.PersonajeSERVER;
import com.dirty.utiles.RenderSERVER;
import com.dirty.utiles.UtilesSERVER;

public class JumpingTOWERRED extends Game {
	public UtilesSERVER utiles;
	public SpriteBatch sb;
	public BatmanSERVER batman;


	@Override
	public void create() {
		utiles = new UtilesSERVER();
		sb = utiles.getSb();
		RenderSERVER.app = this;
	
		setScreen(new PantallaMenuSERVER(this, sb));
		//setScreen( new PantallaFinSERVER(sb, "ambosPerdieron"));

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

