package com.dirty.interfaz;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dirty.hilos.Cronometro;
import com.dirty.juego.JumpingTOWER;
import com.dirty.jugadores.Jugador1;
import com.dirty.jugadores.Jugador2;
import com.dirty.pantallas.PantallaJuego;
import com.dirty.personajes.Personaje;
import com.dirty.utiles.Utiles;

public class Hud {
	public Stage stage;
	private Viewport viewport;
	private Personaje personaje;
	private int tiempo;
	private float contarTiempo;
	private int nivel;
	private int puntuacion;
	Utiles utiles = new Utiles();
	SpriteBatch sb; 
	Label jugadorTextoLabel;//ESTO
	Label jugadorLabel;//ESTO
	Label jugadorLabel2;//ESTO
	Label puntosTextoLabel;
	Label puntosLabel;
	Label timerLabel;
	Label nivelLabel;
	Label nivelTextoLabel;
	Label timerTextoLabel;
	Label vidaTextoLabel;
	Label vidaLabel;
	Label puntosLabel2;//ESTO
	Label vidaLabel2;//ESTO
	Label timerLabel2; //ESTO
	Label nivelLabel2; //ESTO 
	Label[] labels = new Label[9]; //ESTO
	String tiempoJuegoArray[] = new String[3];
	String tiempoJuego;
	String puntosJugadorStringArray[] = new String[3];
	Label energiaTextoLabel;
	Label energiaLabel;
	Label energiaLabel2;

	public Hud() {
		sb = utiles.getSb();
		contarTiempo = 0;
		puntuacion = 0;
		// Aca deberia estar el nivel el cual esta el personaje
		nivel = 1;

		viewport = new FitViewport(Utiles.vpWidth, Utiles.vpHeight, new OrthographicCamera());
		stage = new Stage(viewport, sb);

		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		//TO-DO
		// personajeLabel=new Label();
		//Falta nombre personaje que elige
		
		jugadorTextoLabel = new Label("JUGADOR", new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //ESTO
		setLabels(jugadorTextoLabel);
		cambiarTamanioLabel(jugadorTextoLabel);
		
		jugadorLabel = new Label(String.format("%01d", 1), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //ESTO
		setLabels(jugadorLabel);
		cambiarTamanioLabel(jugadorLabel);
		
		jugadorLabel2 = new Label(String.format("%01d", 2), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //ESTO
		setLabels(jugadorLabel2);
		cambiarTamanioLabel(jugadorLabel2);
		
		puntosTextoLabel = new Label("PUNTOS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setLabels(puntosTextoLabel);
		cambiarTamanioLabel(puntosTextoLabel);
		
		puntosLabel = new Label(String.format("%03d", puntuacion), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setLabels(puntosLabel);
		cambiarTamanioLabel(puntosLabel);
		
		puntosLabel2 = new Label(String.format("%03d", puntuacion), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //ESTO
		setLabels(puntosLabel2);
		cambiarTamanioLabel(puntosLabel2);
		
		
		timerTextoLabel = new Label("TIEMPO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setLabels(timerTextoLabel);
		cambiarTamanioLabel(timerTextoLabel);
		
		timerLabel = new Label(String.format("%03d", tiempo), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setLabels(timerLabel);
		cambiarTamanioLabel(timerLabel);
		
		timerLabel2 = new Label(String.format("%03d", tiempo), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //ESTO
		setLabels(timerLabel2);
		cambiarTamanioLabel(timerLabel2);
		
		nivelTextoLabel = new Label("Nivel", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setLabels(nivelTextoLabel);
		cambiarTamanioLabel(nivelTextoLabel);
		
		nivelLabel = new Label(String.format("%01d", nivel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		setLabels(nivelLabel);
		cambiarTamanioLabel(nivelLabel);
		
		nivelLabel2 = new Label(String.format("%01d", nivel), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); //ESTO
		setLabels(nivelLabel2);
		cambiarTamanioLabel(nivelLabel2);
		
		vidaTextoLabel = new Label("VIDA", new Label.LabelStyle(new BitmapFont(), Color.RED));
		setLabels(vidaTextoLabel);
		cambiarTamanioLabel(vidaTextoLabel);
		
		vidaLabel = new Label(String.format("%03d", puntuacion), new Label.LabelStyle(new BitmapFont(), Color.RED));
		setLabels(vidaLabel);
		cambiarTamanioLabel(vidaLabel);
		
		vidaLabel2 = new Label(String.format("%03d", puntuacion), new Label.LabelStyle(new BitmapFont(), Color.RED)); //ESTO
		setLabels(vidaLabel2);
		cambiarTamanioLabel(vidaLabel2);
		
		energiaTextoLabel=new Label("ENERGIA", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
		setLabels(energiaTextoLabel);
		cambiarTamanioLabel(energiaTextoLabel);
		
		energiaLabel = new Label(String.format("%03d", puntuacion), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
		setLabels(energiaLabel);
		cambiarTamanioLabel(energiaLabel);
		
		energiaLabel2 = new Label(String.format("%03d", puntuacion), new Label.LabelStyle(new BitmapFont(), Color.YELLOW)); //ESTO
		setLabels(energiaLabel2);
		cambiarTamanioLabel(energiaLabel2);
		
		
		//table.add(personajeTextoLabel).expandX();
		//TO-DO
		table.add(jugadorTextoLabel).expandX(); //ESTO
		table.add(puntosTextoLabel).expandX();
		table.add(timerTextoLabel).expandX();
		table.add(nivelTextoLabel).expandX();
		table.add(vidaTextoLabel).expandX();
		table.add(energiaTextoLabel).expandX();
		table.add().expandX();
		table.row();

		
		//TO-DO

		table.add(jugadorLabel).expandX(); //ESTO
		table.add(puntosLabel).expandX();
		table.add(timerLabel).expandX();
		table.add(nivelLabel).expandX();
		table.add(vidaLabel).expandX();
		table.add(energiaLabel).expandX();
		table.add().expandX();//ESTO
		table.row();//ESTO
		
		table.add(jugadorLabel2).expandX(); //ESTO
		table.add(puntosLabel2).expandX();//ESTO
		table.add().expandX();//ESTO
		table.add().expandX();//ESTO
		table.add(vidaLabel2).expandX();//E
		table.add(energiaLabel2).expandX();

		stage.addActor(table);//ESTO
	}

	public Stage getStage() {
		return this.stage; // retorna la columna enorme que tiene el hud.
	}
	
	public void setLabels(Label label)
	{
		for(int i = 0; i<labels.length;i++)
		{
			if(labels[i]==null)
			{
                labels[i] = label; 
			}
		}
	}
	
	public void cambiarTamanioLabel(Label label)
	{
		label.setFontScale(1.7f);
	}

	public void actualizarDatos(Cronometro cronometro) {
		float valor = cronometro.getTiempoJuego();
		tiempoJuego = Float.toString(valor);
		tiempoJuegoArray = tiempoJuego.split("\\.");
		timerLabel.setText(tiempoJuegoArray[0]);
	
		//energiaLabel.setText((int) jugador1.getPersonajeJugador().getEnergia());
		
	}

	public void actualizarPuntosVidaHUD(int idJugador, float puntos, int vida, int energia) {

		if(idJugador==1){
			String puntosJugadorString = String.valueOf(puntos);
			String puntosJugadorStringArray[] = puntosJugadorString.split(("\\."));
			puntosLabel.setText(puntosJugadorStringArray[0]);
			vidaLabel.setText(vida);
			energiaLabel.setText(energia);
		}
		
		if(idJugador == 2) {
			String puntosJugadorString = String.valueOf(puntos);
			String puntosJugadorStringArray[] = puntosJugadorString.split(("\\."));
			puntosLabel2.setText(puntosJugadorStringArray[0]);
			vidaLabel2.setText(vida);
			energiaLabel2.setText(energia);
		}
		
		
		
		
	}

}
