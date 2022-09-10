package com.dirty.utiles;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.configuraciones.ConfiguracionesSERVER;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.personajes.PersonajeSERVER;


public abstract class InteractiveTileObjectSERVER {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle rect;
	protected Body body;
	protected Fixture fixture;
	protected JugadorBASESERVER jugador;

	public InteractiveTileObjectSERVER(TiledMap map, World world, Rectangle rect, JugadorBASESERVER jugador) {
		float ppm = ConfiguracionesSERVER.PPM.getValor();
		this.world = world;
		this.map = map;
		this.jugador=jugador;

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape pS = new PolygonShape();

		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((rect.getX() + rect.getWidth() / 2) / UtilesSERVER.PPM,
				(rect.getY() + rect.getHeight() / 2) / UtilesSERVER.PPM);
		// Se posicionan los rectangulos
		body = world.createBody(bdef);

		// Se le asigna tamaño a los rectangulos de los objetos de colisiones
		pS.setAsBox((rect.getWidth() / 2) / UtilesSERVER.PPM, (rect.getHeight() / 2) / UtilesSERVER.PPM);
		fdef.shape = pS;
		fixture = body.createFixture(fdef);
		this.body = body;

	}

	// public abstract void choque(Personaje personaje);

	public void setCategoryFilter(short filterBit) {
		Filter filter = new Filter();
		filter.categoryBits = filterBit;
		fixture.setFilterData(filter);
	}
	
	public abstract void choque(JugadorBASESERVER jugador);
}