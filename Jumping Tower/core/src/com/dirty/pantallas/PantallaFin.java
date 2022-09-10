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


public class PantallaFin implements Screen {

	Imagen fondoMenu;
	SpriteBatch sprite;
	Texto opciones[] = new Texto[1];
	Texto opciones2[] = new Texto[2];
	String textos[] = { "SALIR"};
	String textos2[] = {"GANASTE", "PERDISTE"};
	private Color colores;
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	Entradas entradas;
	String nombreJugador;
	private JumpingTOWER appGame;
    int i = 0;

    boolean encontrado = false; 
    String opcion; 
  
    
	
	public PantallaFin(SpriteBatch sb, String opcion) {
		
		this.opcion = opcion;
		sprite = sb; 
		if(opcion.equals("ambosGanaron")) {
			
			colores=Color.GREEN;
			fondoMenu = new Imagen(Recursos.FONDOGANASTE);
			
			caracTextoPantalla(colores,0);
			
		}else if(opcion.equals("ambosPerdieron") ){
			
			colores=Color.RED;
			fondoMenu = new Imagen(Recursos.FONDOPERDISTE); //mismo fondo en rojo para indicar que perdio
			//aca entra
		
			caracTextoPantalla(colores,1);
		}
		
		 // Colocamos que la imagen de fondo tenga el ancho y alto de la pantalla
		fondoMenu.setSize(37, 36);
		
		
		//fondoMenu.setPosition((Utiles.vpWidth/99.3f)/2.38f, (Utiles.vpHeight/Utiles.PPM)/4f); // Colocamos que la imagen de fondo tenga el
																		// ancho y alto de la pantalla
		
		entradas = Recursos.entradas;
		Gdx.input.setInputProcessor(entradas);
		

		

		 //ACA VERIFICA QUE VALOR (GANASTE/PERDISTE) LE CORRESPONDE A CADA JUGADOR SEGÚN ENVIE SU MENSAJE.
			
		
		
		
		
	
	
	
	}

	@Override
	public void show() {
		
		
	}

	private void caracTextoPantalla( Color colores, int texto) {
		int avance = 30;
		
	
		for ( int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTEMENU, 50, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Utiles.vpHeight - 83) - (opciones[i].getAncho() / 2),
					((Utiles.vpHeight / 5) + (opciones[i].getAlto()))
							- ((opciones[i].getAlto() + avance) * i));
			
		
	
		
	}
		
	
		
		opciones2[texto] = new Texto(Recursos.FUENTEMENU, 5, Color.RED, true);
		opciones2[texto].setTexto(textos2[texto]);
		opciones2[texto].setPosition(Utiles.vpHeight/2, Utiles.vpWidth/2);
	
	
		
		
	
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
				// la funcion
		Render.limpiarPantalla();
	sprite.end();
		sprite.begin(); // Comenzamos creando un batch para que sepa que vamos a dibujar la pantalla
		fondoMenu.dibujar(); // Dibujamos el fondo menu!

		// Dibujamos lo que sea texto en la pantalla en este caso "JUGAR"
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		if(this.opcion.equals("ambosGanaron")) {
			opciones2[0].dibujar();
		} if(this.opcion.equals("ambosPerdieron")) {
			opciones2[1].dibujar();
		
		}
		
		/*for (int i = 0; i < opciones2.length; i++) {
			if(opciones2[i]==null)
			{
				//EN CASO DE QUE BORREN EL CODIGO LO QE TIENE EL ARRAY ENTONCES TIENE QUE NO DIBUJARSE
			}else {
				opciones2[i].dibujar();
			}
			
		}
		
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

		/*for (int i = 0; i < opciones.length; i++) {
			if (i == (opcElegida - 1)) {
				opciones[i].setColor(Color.GREEN);
			} else {
				opciones[i].setColor(Color.WHITE);
			}
		}*/
		//caracTextoPantalla(colores);

		if (entradas.isEnter() || (entradas.isClick()) && mouseArriba) {
			if(opcElegida == 1 )
			{
				Gdx.app.exit();
				//deberias resetear el server.
			}
			if (opcElegida == 2) {
				// sale del juego
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
