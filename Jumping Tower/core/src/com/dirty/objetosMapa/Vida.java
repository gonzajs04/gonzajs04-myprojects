package com.dirty.objetosMapa;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.mapas.Mapa;
import com.dirty.utiles.InteractiveTileObject;
import com.dirty.utiles.Utiles;

import configuraciones.ConfiguracionesCliente;

public class Vida extends InteractiveTileObject {
	private int cont;
	private Map mapa;

	public Vida(World world, TiledMap map, Rectangle rect, JugadorBASE jugador) {
		super(map,world, rect, jugador);
		fixture.setUserData(this);
		setCategoryFilter(Utiles.VIDA_BIT);
		mapa=map;

	}

	@Override
	public void choque(JugadorBASE jugador) {
		
		

		if (jugador.getPersonajeJugador().getNombre().equals("Goku")) {
			System.out.println("Entro a recuperar vida goku");

			ConfiguracionesCliente.hc.enviarMensaje("GokuChocoVida");

		} else if (jugador.getPersonajeJugador().getNombre().equals("Batman")) {
			System.out.println("Entro a recuperar vida batman");
			ConfiguracionesCliente.hc.enviarMensaje("BatmanChocoVida");

		}

		destruirObjetoVida();
		sacarObjetoVidaMap();

	}

	private void sacarObjetoVidaMap() {
		getCell().setTile(null);
		System.out.println("Entro a sacar objeto");

	}

	private void destruirObjetoVida() {
		System.out.println("Entro a destruir objeto");

		setCategoryFilter(Utiles.DESTROYED_BIT);

	}

	public TiledMapTileLayer.Cell getCell() {

	

		TiledMapTileLayer layer = (TiledMapTileLayer) mapa.getLayers().get(7);
		return layer.getCell((int) (body.getPosition().x * Utiles.PPM) / 32,
				(int) (body.getPosition().y * Utiles.PPM) / 32);
	}

}
