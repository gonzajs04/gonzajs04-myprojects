package com.dirty.objetosMapa;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.utiles.InteractiveTileObject;
import com.dirty.utiles.Utiles;

import configuraciones.ConfiguracionesCliente;

public class Monedas extends InteractiveTileObject {
	private Map mapa;
	int cont = 0;

	public Monedas(World world, TiledMap map, Rectangle rect, JugadorBASE jugador) {
		super(map, world, rect, jugador);
		fixture.setUserData(this);
		setCategoryFilter(Utiles.COIN_BIT);
		mapa = map;

	}

	@Override
	public void choque(JugadorBASE jugador) {

		if (jugador.personaje.getNombre().equals("Goku")) {

			ConfiguracionesCliente.hc.enviarMensaje("ChocoMonedaGoku");

		} else if (jugador.personaje.getNombre().equals("Batman")) {

			ConfiguracionesCliente.hc.enviarMensaje("ChocoMonedaBatman");

		}
		destruirMoneda();
		sacarMonedaMapa();

	}

	private void sacarMonedaMapa() {
		getCell().setTile(null);

	}

	private void destruirMoneda() {
		
		setCategoryFilter(Utiles.DESTROYED_BIT);

	}

	public TiledMapTileLayer.Cell getCell() {
		
		cont++;

		TiledMapTileLayer layer = (TiledMapTileLayer) mapa.getLayers().get(6);
		return layer.getCell((int) (body.getPosition().x * Utiles.PPM) / 32,
				(int) (body.getPosition().y * Utiles.PPM) / 32);
	}

}
