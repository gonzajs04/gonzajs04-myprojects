package com.dirty.utiles;




import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.math.Rectangle;

import com.dirty.jugadores.JugadorBASESERVER;

import com.dirty.mapas.MapaSERVER;

import com.dirty.objetosMapa.BanderaSERVER;

import com.dirty.objetosMapa.CieloSERVER;
import com.dirty.objetosMapa.MonedasSERVER;
import com.dirty.objetosMapa.ObjetoSaltarSERVER;

import com.dirty.objetosMapa.PiedraSERVER;
import com.dirty.objetosMapa.PinchosSERVER;
import com.dirty.objetosMapa.VidaSERVER;
import com.dirty.personajes.PersonajeSERVER;

public class B2WorldCreatorSERVER {

	protected Fixture fixture;
	private World world;
	private TiledMap map;
	private JugadorBASESERVER jugador1;
	private JugadorBASESERVER jugador2; 

	public B2WorldCreatorSERVER(World world, TiledMap map, MapaSERVER mapa) {
		this.world = world;
		this.map = map;
		
		
		
			
	}
	
	public void setJugador1(JugadorBASESERVER jugador) {
		this.jugador1 = jugador; 
	}
	public void setJugador2(JugadorBASESERVER jugador2) {
		this.jugador2 = jugador2;
	}

	public void crearCoaliciones(JugadorBASESERVER jugador) {
		for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new ObjetoSaltarSERVER(world, map, rect, jugador); 
			//System.out.println("Piso");
		} 
		
		for (MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new PiedraSERVER(world, map, rect, jugador); 
			//System.out.println("Piedra");
			
		
		} 
		
		for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new BanderaSERVER(world, map, rect, jugador); 
			//System.out.println("Bandera");
		} 


		for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new CieloSERVER(world, map, rect, jugador); 
			//System.out.println("Bandera");
		} 
		
		for (MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new PinchosSERVER(world, map, rect, jugador); 
			//System.out.println("Bandera");
		} 
		
		for (MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new MonedasSERVER(world, map, rect, jugador); 
	
		} 
		
		for (MapObject object : map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new VidaSERVER(map, world, rect, jugador); 
	
		} 

		
	}

}
