package com.dirty.pantallas;

import com.badlogic.gdx.Gdx;
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




public class PantallaMenuSERVER implements Screen {

	ImagenSERVER fondoMenu;
	SpriteBatch sprite;
	TextoSERVER opciones[] = new TextoSERVER[3];
	String textos[] = { "NUEVA PARTIDA", "REGLAS","SALIR"};
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	EntradasSERVER entradas;
	String nombreJugador;
	private JumpingTOWERRED appGame;


	
	public PantallaMenuSERVER(JumpingTOWERRED jumpingTOWER, SpriteBatch sb) {
		this.appGame = jumpingTOWER;
		this.sprite = sb;
	}

	@Override
	public void show() {
	
		fondoMenu = new ImagenSERVER(RecursosSERVER.FONDO);
		fondoMenu.setSize(UtilesSERVER.vpWidth, UtilesSERVER.vpHeight); // Colocamos que la imagen de fondo tenga el ancho y alto de la pantalla
		
		entradas = RecursosSERVER.entradas;
		Gdx.input.setInputProcessor(entradas);
		

		int avance = 30;
	
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new TextoSERVER(RecursosSERVER.FUENTEMENU, 50, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((UtilesSERVER.vpHeight - 83) - (opciones[i].getAncho() / 2),
					((UtilesSERVER.vpHeight / 2) + (opciones[0].getAlto() / 2))
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
				RenderSERVER.app.setScreen(new PantallaJuegoSERVER(RenderSERVER.app));
			} 
			else if(opcElegida==2) {
				RenderSERVER.app.setScreen(new PantallaReglasSERVER(RenderSERVER.app,this.sprite));
			}
			
			 else if (opcElegida == 3) {
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
