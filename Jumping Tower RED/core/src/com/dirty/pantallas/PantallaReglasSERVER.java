package com.dirty.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.dirty.elementos.ImagenSERVER;

import com.dirty.elementos.TextoSERVER;
import com.dirty.entradas.EntradasSERVER;
import com.dirty.juego.JumpingTOWERRED;
import com.dirty.utiles.RecursosSERVER;

import com.dirty.utiles.RenderSERVER;
import com.dirty.utiles.UtilesSERVER;




public class PantallaReglasSERVER implements Screen{
	
	

	ImagenSERVER fondoMenu;
	SpriteBatch sprite;
	TextoSERVER opciones[] = new TextoSERVER[8];
	String textos[] = { "Si llegas a 0 de vida pierdes",  "Si llegas a 1500 puntos, ganas","Si tocas la bandera ganas","Si se acaba el tiempo, pierdes","Si agarras monedas sumas 200 Puntos","Cada un segundo se le restara 1HP","Los botiquines brindaran 50HP","Salir de reglas = RETROCESO","Salir del juego = ESCAPE"};
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	EntradasSERVER entradas;
	String nombreJugador;
	private JumpingTOWERRED appGame;
	
	public PantallaReglasSERVER(JumpingTOWERRED app, SpriteBatch sb ) {

		this.sprite=sb;
		app=appGame;
	}

	@Override
	public void show() {
		fondoMenu = new ImagenSERVER(RecursosSERVER.FONDO);
		fondoMenu.setSize(UtilesSERVER.vpWidth, UtilesSERVER.vpHeight); // Colocamos que la imagen de fondo tenga el ancho y alto de la pantalla
		
		entradas = RecursosSERVER.entradas;
		Gdx.input.setInputProcessor(entradas);
		

		int avance = 30;
	
//		for (int i = 0; i < opciones.length; i++) {
//			opciones[i] = new TextoSERVER(RecursosSERVER.FUENTEMENU, 50, Color.WHITE, true);
//			opciones[i].setTexto(textos[i]);
//			opciones[i].setPosition((UtilesSERVER.vpHeight - 83) - (opciones[i].getAncho() / 2),
//					((UtilesSERVER.vpHeight / 2) + (opciones[0].getAlto() / 2))
//							- ((opciones[i].getAlto() + avance) * i));
//		}
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new TextoSERVER(RecursosSERVER.FUENTEMENU, 50, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((UtilesSERVER.vpHeight - 83) - (opciones[i].getAncho() / 2),
					((UtilesSERVER.vpHeight / 2) + ((opciones[0].getAlto() / 2)+90))
							- ((opciones[i].getAlto() + 25) * i));
		}
		
	}

	@Override
	public void render(float delta) {
	
		this.sprite.end();
		this.sprite.begin();
		fondoMenu.dibujar();
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
	
		if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
			RenderSERVER.app.setScreen(new PantallaMenuSERVER(appGame,this.sprite));
	
		}
		
		
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
