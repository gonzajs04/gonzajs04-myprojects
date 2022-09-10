package com.dirty.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dirty.elementos.Imagen;
import com.dirty.elementos.Texto;

import com.dirty.entradas.Entradas;
import com.dirty.juego.JumpingTOWER;
import com.dirty.utiles.Recursos;

import com.dirty.utiles.Render;
import com.dirty.utiles.Utiles;


import configuraciones.ConfiguracionesCliente;

public class PantallaReglas implements Screen {
	

	Imagen fondoMenu;
	SpriteBatch sprite;
	Texto opciones[] = new Texto[8];
	String textos[] = { "Si llegas a 0 de vida pierdes",  "Si llegas a 1500 puntos, ganas","Si tocas la bandera ganas","Si se acaba el tiempo, pierdes","Si agarras monedas sumas 200 Puntos","Cada un segundo se le restara 1HP","Salir de reglas = RETROCESO","Los botiquines brindaran 50HP", "Salir del juego = ESCAPE"};
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	Entradas entradas;
	String nombreJugador;
	private JumpingTOWER appGame;

	public PantallaReglas(JumpingTOWER app, SpriteBatch sb ) {
		this.sprite=sb;
	
	}

	@Override
	public void show() {
		fondoMenu = new Imagen(Recursos.FONDO);
		fondoMenu.setSize(Utiles.vpWidth, Utiles.vpHeight); // Colocamos que la imagen de fondo tenga el ancho y alto de la pantalla
		
		entradas = Recursos.entradas;
		Gdx.input.setInputProcessor(entradas);
		

		int avance = 30;
	
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTEMENU, 50, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Utiles.vpHeight - 83) - (opciones[i].getAncho() / 2),
					((Utiles.vpHeight / 2) + ((opciones[0].getAlto() / 2)+90))
							- ((opciones[i].getAlto() + 25) * i));
		}
		
	}

	@Override
	public void render(float delta) {
		sprite.end();
		sprite.begin(); // Comenzamos creando un batch para que sepa que vamos a dibujar la pantalla
		fondoMenu.dibujar(); // Dibujamos el fondo menu!
		// Dibujamos lo que sea texto en la pantalla en este caso "JUGAR"
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
			Render.app.setScreen(new PantallaMenu(appGame,this.sprite));
			
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
