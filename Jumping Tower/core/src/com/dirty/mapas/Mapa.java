package com.dirty.mapas;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.utiles.Utiles;

import configuraciones.Configuraciones;


public class Mapa {
	private TmxMapLoader cargadorMapa;
	private TiledMap mapa;
	private OrthogonalTiledMapRenderer renderizarMapa;
	private String direccion = ("Mapa.tmx");
	private MapProperties propiedadesMapa; 
	private Rectangle rect; 

	
	private int alto;
	private int ancho; 
	private int altoPixelTiled;
	private int anchoPixelTiled;
	
	
	public Mapa() 
	{
	float ppm=Configuraciones.PPM.getValor();
		cargadorMapa  =  new TmxMapLoader();
		mapa  =  cargadorMapa.load(direccion);
		renderizarMapa  =  new OrthogonalTiledMapRenderer(mapa,1/Utiles.PPM);
		propiedadesMapa = mapa.getProperties();
		
		alto = propiedadesMapa.get("height", Integer.class);
		ancho = propiedadesMapa.get("width", Integer.class);
		altoPixelTiled = propiedadesMapa.get("tileheight", Integer.class);
		anchoPixelTiled = propiedadesMapa.get("tilewidth", Integer.class);
		
		alto = alto * altoPixelTiled;
		ancho = ancho * anchoPixelTiled; 
	}
	

	public TmxMapLoader getCargadorMapa() {
		return cargadorMapa;
	}
	public TiledMap getMapa() {
		return mapa;
	}
	public OrthogonalTiledMapRenderer getRenderizarMapa() {
		return renderizarMapa;
	}
	
	public float getAnchoMapa() 
	{
		return this.ancho; 
	}
	
	public float getAltoMapa() 
	{
		return this.alto;
	}

	public void realizarHitboxObjetoSaltar(BodyDef bdef, Body cuerpo, FixtureDef fdef, PolygonShape shape, World mundo) {
	
		
		bdef.type=BodyDef.BodyType.StaticBody;
		bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
		
		cuerpo= mundo.createBody(bdef);
		shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
		fdef.shape=shape;
		cuerpo.createFixture(fdef);
		
	}

		
		
	}
	
	

	
	

