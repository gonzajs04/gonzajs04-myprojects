package com.dirty.utiles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.mapas.Mapa;
import com.dirty.objetosMapa.Bandera;
import com.dirty.objetosMapa.Cielo;
import com.dirty.objetosMapa.Monedas;
import com.dirty.objetosMapa.ObjetoSaltar;
import com.dirty.objetosMapa.Piedra;
import com.dirty.objetosMapa.Pinchos;
import com.dirty.objetosMapa.Vida;
import com.dirty.personajes.Personaje;

public class B2WorldCreator {

	protected Fixture fixture;
	private World world;
	private TiledMap map;
	private JugadorBASE jugador1;
	private JugadorBASE jugador2;

	public B2WorldCreator(World world, TiledMap map, Mapa mapa) {
		this.world = world;
		this.map = map;

	}

	public void setJugador1(JugadorBASE jugador) {
		this.jugador1 = jugador;
	}

	public void setJugador2(JugadorBASE jugador2) {
		this.jugador2 = jugador2;
	}

	public void crearCoaliciones(JugadorBASE jugador) {
		for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new ObjetoSaltar(world, map, rect, jugador);
			// System.out.println("Piso");
		}

		for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Piedra(world, map, rect, jugador);
			// System.out.println("Piedra");

		}

		for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Bandera(world, map, rect, jugador);
			// System.out.println("Bandera");
		}

		for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Cielo(world, map, rect, jugador);
			// System.out.println("Bandera");
		}

		for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Pinchos(world, map, rect, jugador);
			// System.out.println("Bandera");
		}

		for (MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Monedas(world, map, rect, jugador);

		}

		for (MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Vida(world, map, rect, jugador);

		}

	}

}
