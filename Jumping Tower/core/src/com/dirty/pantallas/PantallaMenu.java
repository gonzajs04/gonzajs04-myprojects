package com.dirty.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dirty.elementos.Imagen;
import com.dirty.elementos.Texto;
import com.dirty.entradas.*;
import com.dirty.juego.JumpingTOWER;
import com.dirty.utiles.Recursos;
import com.dirty.utiles.Render;
import com.dirty.utiles.Utiles;




public class PantallaMenu implements Screen {

	Imagen fondoMenu;
	SpriteBatch sprite;
	Texto opciones[] = new Texto[3];
	String textos[] = { "NUEVA PARTIDA", "REGLAS",  "SALIR"};
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	Entradas entradas;
	String nombreJugador;
	private JumpingTOWER appGame;


	
	public PantallaMenu(JumpingTOWER jumpingTOWER, SpriteBatch sb) {
		this.sprite = sb;
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
					((Utiles.vpHeight / 2) + (opciones[0].getAlto() / 2))
							- ((opciones[i].getAlto() + avance) * i));
		}
		
	
	}

	@Override
	public void render(float delta) { // delta son los milisegundos que tarda en mostrarse por completo lo que tiene
										// la funcion
		sprite.end();
		sprite.begin(); // Comenzamos creando un batch para que sepa que vamos a dibujar la pantalla
		fondoMenu.dibujar(); // Dibujamos el fondo menu!
		// Dibujamos lo que sea texto en la pantalla en este caso "JUGAR"
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		

		tiempo += delta;

		// control del color de los textos del menu mediante mouse
		int cont = 0;
		for (int i = 0; i < opciones.length; i++) {
			if ((entradas.getMouseX() >= opciones[i].getX())
					&& (entradas.getMouseX() <= (opciones[i].getX() + opciones[i].getAncho()))) {
				if ((entradas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto())
						&& (entradas.getMouseY() <= opciones[i].getY())) {
					opcElegida = i + 1;
					cont++;
				}
			}
		}

		if (cont != 0) {
			mouseArriba = true;
		} else {
			mouseArriba = false;
		}

		if (entradas.isAbajo()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opcElegida++;
				if (opcElegida > 2) {
					opcElegida = 1;
				}
			}
		}

		if (entradas.isArriba()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opcElegida--;
				if (opcElegida < 1) {
					opcElegida = 2;
				}
			}
		}

		for (int i = 0; i < opciones.length; i++) {
			if (i == (opcElegida - 1)) {
				opciones[i].setColor(Color.CYAN);
			} else {
				opciones[i].setColor(Color.WHITE);
			}
		}

		if (entradas.isEnter() || (entradas.isClick()) && mouseArriba) {
			if (opcElegida == 1) {
				this.dispose();
				Render.app.setScreen(new PantallaJuego(Render.app));
			} 
			 else if (opcElegida == 2) {
				 Render.app.setScreen(new PantallaReglas(Render.app,sprite));
				
			}
			 else if(opcElegida==3) {
				 Gdx.app.exit();// sale del juego
			 }
		}
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		
	}

}
