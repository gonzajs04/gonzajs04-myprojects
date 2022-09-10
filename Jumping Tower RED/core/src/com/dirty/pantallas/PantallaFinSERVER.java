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

public class PantallaFinSERVER implements Screen {

	ImagenSERVER fondoMenu;
	SpriteBatch sprite;
	TextoSERVER opciones[] = new TextoSERVER[1];
	TextoSERVER opciones2[] = new TextoSERVER[2];
	String textos[] = { "SALIR" };
	String textos2[] = { "GANASTE", "PERDISTE" };
	private Color colores;
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	EntradasSERVER entradas;
	String nombreJugador;
	String opcion;
	private JumpingTOWERRED appGame;
	int i = 0;

	boolean encontrado = false;

	public PantallaFinSERVER(SpriteBatch sb, String opcion) {
		this. opcion=opcion;
		sprite = sb;
		
		if (opcion.equals("ambosGanaron")) {
			colores = Color.GREEN;
			fondoMenu = new ImagenSERVER(RecursosSERVER.FONDOGANASTE);
			caracTextoPantalla(colores);

		} else if(opcion.equals("ambosPerdieron")) {	
			colores = Color.RED;
			fondoMenu = new ImagenSERVER(RecursosSERVER.FONDOPERDISTE); // mismo fondo en rojo para indicar que perdio
			caracTextoPantalla(colores);
			//aca entra
		}
		
		fondoMenu.setSize(37, 36);
		//fondoMenu.setPosition((UtilesSERVER.vpWidth / 99.3f)/2.38f, (UtilesSERVER.vpHeight/UtilesSERVER.PPM)/4f); // Colocamos que la imagen de fondo tenga el
																		// ancho y alto de la pantalla

		entradas = RecursosSERVER.entradas;
		Gdx.input.setInputProcessor(entradas);

		/*
		 * do { //ACA VERIFICA QUE VALOR (GANASTE/PERDISTE) LE CORRESPONDE A CADA
		 * JUGADOR SEGÚN ENVIE SU MENSAJE.
		 * 
		 * if(valor.equals(textos2[i])) {
		 * 
		 * opciones2[i] = new Texto(Recursos.FUENTEMENU, 90, Color.WHITE, true);
		 * opciones2[i].setTexto(textos2[i]); opciones2[i].setPosition((Utiles.vpHeight
		 * - 83) - (opciones2[i].getAncho() / 2), ((Utiles.vpHeight / 2) +
		 * (opciones2[i].getAlto() / 2)) - ((opciones2[i].getAlto() + avance) * i));
		 * encontrado = true; } i++;
		 * 
		 * }while(!encontrado);
		 * 
		 * 
		 * 
		 * 
		 * }
		 */
	}

	@Override
	public void show() {
		
	}

	private void caracTextoPantalla(Color colores) {
		int avance = 30;

		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new TextoSERVER(RecursosSERVER.FUENTEMENU, 7, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
//			opciones[i].setPosition((UtilesSERVER.vpHeight - 83) - (opciones[i].getAncho() / 2),
//					((UtilesSERVER.vpHeight / 5) + (opciones[i].getAlto())) - ((opciones[i].getAlto() + avance) * i));
			
			opciones[i].setPosition((UtilesSERVER.vpHeight /2),(UtilesSERVER.vpHeight / 2)) ;
				
		}

		for (int i = 0; i < opciones.length; i++) {
			if (i == (opcElegida - 1)) {
				opciones[i].setColor(colores);
			} else {
				opciones[i].setColor(Color.WHITE);
			}
		}

	}

	@Override
	public void render(float delta) { // delta son los milisegundos que tarda en mostrarse por completo lo que tiene
		RenderSERVER.limpiarPantalla();				// la funcion
		sprite.end();
		sprite.begin(); // Comenzamos creando un batch para que sepa que vamos a dibujar la pantalla
		fondoMenu.dibujar(); // Dibujamos el fondo menu!
		// Dibujamos lo que sea texto en la pantalla en este caso "JUGAR"
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}

		/*
		 * for (int i = 0; i < opciones2.length; i++) { if(opciones2[i]==null) { //EN
		 * CASO DE QUE BORREN EL CODIGO LO QE TIENE EL ARRAY ENTONCES TIENE QUE NO
		 * DIBUJARSE }else { opciones2[i].dibujar(); }
		 * 
		 * }
		 * 
		 */
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
				if (opcElegida > 1) {
					opcElegida = 1;
				}
			}
		}

		if (entradas.isArriba()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opcElegida--;
				if (opcElegida < 1) {
					opcElegida = 1;
				}
			}
		}

		/*
		 * for (int i = 0; i < opciones.length; i++) { if (i == (opcElegida - 1)) {
		 * opciones[i].setColor(Color.GREEN); } else {
		 * opciones[i].setColor(Color.WHITE); } }
		 */
		caracTextoPantalla(colores);

		if (entradas.isEnter() || (entradas.isClick()) && mouseArriba) {
			if (opcElegida == 1) {
				Gdx.app.exit();
				// deberias resetear el server.
			}
			if (opcElegida == 2) {
				Gdx.app.exit();// sale del juego
			}
		}

	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				resize(640,480);
			}
		});
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
