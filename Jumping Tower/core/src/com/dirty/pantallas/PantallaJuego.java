package com.dirty.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.camara.Camara;

import com.dirty.elementos.Texto;
import com.dirty.hilos.Cronometro;
import com.dirty.hilos.HiloTiempo;
import com.dirty.interfaz.Hud;
import com.dirty.juego.JumpingTOWER;
import com.dirty.jugadores.Jugador1;
import com.dirty.jugadores.Jugador2;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.mapas.Mapa;
import com.dirty.musica.Musica;
import com.dirty.personajes.Batman;
import com.dirty.personajes.Goku;
import com.dirty.personajes.Personaje;
import com.dirty.red.HiloCliente;
import com.dirty.utiles.B2WorldCreator;
import com.dirty.utiles.Recursos;
import com.dirty.utiles.Render;
import com.dirty.utiles.Utiles;

import com.dirty.utiles.WorldContactListener;
import com.dirty.utiles.aumentarEnergia;
import com.dirty.utiles.reducirVelocidad;
import com.dirty.utiles.subirCamara;

import configuraciones.ConfiguracionesCliente;

public class PantallaJuego implements Screen {

	private JumpingTOWER game;
	Texture textura;

	private Mapa mapa;
	private Sprite sprite;
	private Utiles utiles = new Utiles();
	private SpriteBatch sb;
	private World mundo;
	private float valorCamY;
	private Camara camara;
	private float valorCamX;
	private Hud hud;
	private HiloTiempo hilos;
	private Box2DDebugRenderer b2dr;
	private Rectangle rect;
	private Body cuerpo;
	private Personaje personaje;
	private Personaje personaje2;
	private TextureAtlas textureAtlas;
	private Cronometro cronometro;
	private Jugador1 jugador1;
	private Jugador2 jugador2;
	private float zoomGeneral;
	private JugadorBASE jugadorBASE;
	private HiloCliente hc;
	private Texto esperandoRival;
	private Musica musica;
	private reducirVelocidad rv;
	private aumentarEnergia ae;
	private boolean setDown1;
	private boolean setRight1;
	private boolean setLeft1;
	private boolean setUp1;
	private boolean setDown2;
	String estadoVictoria;
	private boolean setRight2;
	private boolean setLeft2;
	private boolean setUp2;
	private String tecla;
	private boolean setShift1;
	private boolean setShift2;
	private boolean setSpace1;
	private boolean setSpace2;
	boolean runCamera1 = false;
	boolean runCamera2 = false;
	boolean camaraCreada1 = false;
	boolean camaraCreada2 = false;
	public B2WorldCreator coaliciones;
	public WorldContactListener worldContactListener;

	public PantallaJuego(JumpingTOWER game) {
		Render.limpiarPantalla(); // Limpia la pantalla de "Esperando a otros jugadores".
		crearHiloCliente();
		// textureAtlas = new TextureAtlas("batman.pack");
		// cronometro = new Cronometro();

		this.game = game;
		this.sb = utiles.getSb();

		// camara = new Camara();

		rv = new reducirVelocidad();
		Utiles.rvListener = rv;				//No lo uso
		ae = new aumentarEnergia();
		Utiles.aeListener = ae;

		// hilos = new HiloTiempo(cronometro, jugador1, jugador2);
		// hilos.start();

		/*
		 * BodyDef bdef = new BodyDef(); PolygonShape shape = new PolygonShape();
		 * FixtureDef fdef = new FixtureDef();
		 */

	}

	public void crearPersonajes() {
		personaje = new Batman(mundo, this); // se podria hacer que se creen primero en sv, y dsp se creen aca por un
		// mensaje.
		personaje2 = new Goku(mundo, this); // se podria hacer que se creen primero en sv, y dsp se creen aca por un
		// mensaje.

	}

	public void crearHud() {
		hud = new Hud();
		Utiles.crearHud = true;

	}

	public void crearJugadores() {
		jugador1 = new Jugador1("Player 1");
		jugador2 = new Jugador2("Player 2");

	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	private void crearMundo() {
		mundo = new World(new Vector2(0, (-9.8f * 2)), true);
		b2dr = new Box2DDebugRenderer();

	}

	private void crearCoaliciones() {

		mundo.setContactListener(worldContactListener = new WorldContactListener());
		worldContactListener.setJugador(jugador1, 0); //Le digo que jugadores van a interactuar con el mundo
		worldContactListener.setJugador(jugador2, 1);
		coaliciones = new B2WorldCreator(mundo, mapa.getMapa(), mapa); //Creo hitbox de los elementos del mapa
		coaliciones.setJugador1(jugador1);
		coaliciones.setJugador2(jugador2);  
		coaliciones.crearCoaliciones(jugador1);
		coaliciones.crearCoaliciones(jugador2); //QAue jugadores van a interactuar con coalisiones

	}

	private void setMundo(World mundo) {
		this.mundo = mundo; //La seteo para tenerla disponible en toda la clase

	}

	private World getMundo() {
		return this.mundo;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public void verificarVidaPersonajes() {
		if (jugador1.getPersonajeJugador().getVida() == 0 || jugador2.getPersonajeJugador().getVida() == 0) {

			ConfiguracionesCliente.hc.enviarMensaje("Fin juego P1/P2");

		}

	}

	@Override
	public void render(float delta) {
		// Colorfondo
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!ConfiguracionesCliente.empiezaOnlineC) {
			esperandoRival = new Texto(Recursos.FUENTEMENU, 34, Color.YELLOW, true);
			esperandoRival.setTexto("ESPERANDO QUE TU RIVAL SE CONECTE");
			esperandoRival.setPosition((Utiles.vpWidth / 2) - 230, (Utiles.vpHeight) - 40);
			esperandoRival.dibujar();
		} else {

			if (!Utiles.crearNecesario) {
				crearJugadores();
				crearHud();
				crearMundo();
				setMundo(mundo);
				crearPersonajes();
				setearJugadores();
				crearMapa(); //Creo el mapa a lo ultimo porque el mapa va a tener dentro a los personajes y ya a estar dentro de un mundo
				
				Utiles.crearNecesario = true;
			}

			if (!Utiles.crearCrononometro) { // TODO Esto esta hecho cuando se le manda un mensaje desde el servidor,
												// pero, lo copie aca porque se crasheaba el cliente 2
				cronometro = new Cronometro();
				cronometro.setTiempoJuegoInicio(100);
				hilos = new HiloTiempo(cronometro, jugador1, jugador2);
				hilos.start();
				Utiles.crearCrononometro = true;
				ConfiguracionesCliente.hc.enviarMensaje("Cliente creo cronometro");
			}
			update(delta);
			if (!ConfiguracionesCliente.crearMusica) {
				musica = new Musica("Musica/Title_screen.ogg");
				musica.crearMusica(musica);
				ConfiguracionesCliente.crearMusica=true;
			}

			// Renderizar box2d
			mapa.getRenderizarMapa().render();
			if (ConfiguracionesCliente.crearCamara == 2) {
				b2dr.render(mundo, camara.getCamaraJuego().combined);
			}

			sb.end();
			hud.getStage().draw();
			sb.begin();
			// personaje.draw(game.sb);

			// sprite.draw(sb);
			sb.setProjectionMatrix(camara.getCamaraJuego().combined);

			// sb.setProjectionMatrix(hud.getStage().getCamera().combined);

		}

	}

	private void crearHiloCliente() {
		ConfiguracionesCliente.hc = new HiloCliente(this);
		ConfiguracionesCliente.hc.start(); // Se conecto el jugador

	}

	private void setearJugadores() {
		jugador1.setPersonajeJugador(personaje);
		jugador2.setPersonajeJugador(personaje2);// TODO Auto-generated method stub
		definirFixtures();

	}

	private void definirFixtures() {
		jugador1.getPersonajeJugador().definirFixtura("cuerpo"); // Esto deberia ir en jugador una vez que setea el
		// personaje
		jugador2.getPersonajeJugador().definirFixtura("cuerpo2"); //
		Utiles.yaCreeJugadores = true;

	}

	public void update(float dt) {

		personaje.update(dt);

		mundo.step(1 / 60f, 6, 2);

		/*
		 * if(jugador1.getPersonajeJugador().b2Cuerpo.getPosition().x>7 &&
		 * jugador1.getPersonajeJugador().b2Cuerpo.getPosition().x<29) { //Si el
		 * personaje tiene una posiciï¿½n la cual la camara ve fuera del mapa, entonces
		 * que NO siga moviendo la camara con respecto al movimiento del personaje sino
		 * que se fije
		 * 
		 * }
		 */

		// como se decidio dejar la camara del server y los clientes fija en x. Cuando
		// cada cliente se mueva en X no se va a mover sino solamente en Y.

		// if (cronometro.getTiempoJuego() <= 98) {
		// actualizarCamJugador1();
		// actualizarCamJugador2();

		// }
		if (ConfiguracionesCliente.crearCamara >= 1) {
			camara.getCamaraJuego().update(); // Se actualiza la camara
			if (!ConfiguracionesCliente.resizeCam) { // Si todavia no se cambio el tamaño de ambos jugadores en pantalla
														// que se haga
				resize(1280, 720); // cambia el tamaño de la pantalla por esta
				ConfiguracionesCliente.resizeCam = true;
			}

			mapa.getRenderizarMapa().setView(camara.getCamaraJuego()); // Se renderiza el mapa

		}

		// jugador1.getCamara().getCamaraJuego().update();
		// jugador2.getCamara().getCamaraJuego().update();

		if (ConfiguracionesCliente.resizeCam) {

			verificarVidaPersonajes();
			//verificarAlturaPersonaje();
		}

		hud.actualizarDatos(this.cronometro); // Se actualizan los datos del hud con la info de
												// ambos jugadores
		// camara.getCamaraJuego().position.x =
		// jugador1.getPersonajeJugador().b2Cuerpo.getPosition().x;
		// camara.getCamaraJuego().position.y =
		// jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y;
		if (Utiles.cambiarZoom) {
			camara.getCamaraJuego().zoom = zoomGeneral;
		}
	}
	/*
	 * private void actualizarCamJugador1(float camY, float camX) {
	 * 
	 * jugador1.getCamara().getCamaraJuego().position.y = camY;
	 * jugador1.getCamara().getCamaraJuego().position.x = camX;
	 * camara.getCamaraJuego().position.x =
	 * jugador1.getCamara().getCamaraJuego().position.x;
	 * camara.getCamaraJuego().position.y =
	 * jugador1.getCamara().getCamaraJuego().position.y; Gdx.app.postRunnable(new
	 * Runnable() {
	 * 
	 * @Override public void run() {
	 * 
	 * mapa.getRenderizarMapa().setView(jugador1.getCamara().getCamaraJuego()); //
	 * Se renderiza el mapa }
	 * 
	 * }); }
	 * 
	 * private void actualizarCamJugador2(float camY, float camX) {
	 * 
	 * jugador2.getCamara().getCamaraJuego().position.y = camY;
	 * jugador2.getCamara().getCamaraJuego().position.x = camX;
	 * camara.getCamaraJuego().position.x =
	 * jugador2.getCamara().getCamaraJuego().position.x;
	 * camara.getCamaraJuego().position.y =
	 * jugador2.getCamara().getCamaraJuego().position.y; Gdx.app.postRunnable(new
	 * Runnable() {
	 * 
	 * @Override public void run() {
	 * 
	 * mapa.getRenderizarMapa().setView(jugador2.getCamara().getCamaraJuego()); //
	 * Se renderiza el mapa }
	 * 
	 * }); }
	 */

//	private void verificarAlturaPersonaje() {
//
//		if (ConfiguracionesCliente.verificarVida) {
//
//			if ((jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y * 4) < camara.getCamaraJuego().position.y) {
//
//				ConfiguracionesCliente.hc.enviarMensaje("Cai debajo cam");
//				
//				
//
//			}
//
//			if (jugador2.getPersonajeJugador().b2Cuerpo.getPosition().y * 3 < camara.getCamaraJuego().position.y) {
//
//				ConfiguracionesCliente.hc.enviarMensaje("Cai debajo cam");
//
//			}
//		}
//
//	}

	public void inicializarCamaraCliente(float posX, float posY) {
		camara.getCamaraJuego().position.x = posX;
		camara.getCamaraJuego().position.y = posY;
	}

	public void cambiarZoomCamara(float zoomCam) {
		Utiles.cambiarZoom = true;
		zoomGeneral = zoomCam;
	}

	/*
	 * public void crearCamara(int idJugador) { if (!camaraCreada1) { if (idJugador
	 * == 1) { jugador1.setCamara(camara = new Camara()); Gdx.app.postRunnable(new
	 * Runnable() {
	 * 
	 * @Override public void run() { if (!runCamera1) {
	 * jugador1.getCamara().getGamePort().update(1280, 720);
	 * sb.setProjectionMatrix(jugador1.getCamara().getCamaraJuego().combined);
	 * runCamera1 = true; }
	 * ConfiguracionesCliente.hc.enviarMensaje("Inicializa posicion camara P1");
	 * camaraCreada1 = true; } });
	 * 
	 * }
	 * 
	 * } if (idJugador == 2) { if (!camaraCreada2) { jugador2.setCamara(camara = new
	 * Camara()); Gdx.app.postRunnable(new Runnable() {
	 * 
	 * @Override public void run() { if (!runCamera2) {
	 * jugador2.getCamara().getGamePort().update(1280, 720);
	 * sb.setProjectionMatrix(jugador2.getCamara().getCamaraJuego().combined);
	 * runCamera2 = true; }
	 * 
	 * ConfiguracionesCliente.hc.enviarMensaje("Inicializa posicion camara P2");
	 * camaraCreada2 = true; } });
	 * 
	 * } Utiles.camaraCreada = true; }
	 * 
	 * }
	 */
	/*
	 * 
	 * public void inicializarCamara(int idJugador, float valorXCam) {
	 * 
	 * if (idJugador == 1) {
	 * 
	 * jugador1.camara.getCamaraJuego().position.x = valorXCam;
	 * camara.getCamaraJuego().position.x = valorXCam; //
	 * sb.setProjectionMatrix(jugador1.camara.getCamaraJuego().combined); } if
	 * (idJugador == 2) { jugador2.camara.getCamaraJuego().position.x = valorXCam;
	 * camara.getCamaraJuego().position.x = valorXCam; //
	 * sb.setProjectionMatrix(jugador2.camara.getCamaraJuego().combined); } }
	 */
	/*
	 * public void actualizarCamara(int idJugador, float camY, float camX) {
	 * 
	 * if (idJugador == 1) { actualizarCamJugador1(camY, camX); }
	 * 
	 * if (idJugador == 2) { actualizarCamJugador2(camY, camX); } }
	 */
	public void crearCronometro(float tiempoJuegoInicial) {
		cronometro = new Cronometro();
		cronometro.setTiempoJuegoInicio(tiempoJuegoInicial);
		hilos = new HiloTiempo(cronometro, jugador1, jugador2);
		hilos.start();
		Utiles.crearCrononometro = true;
		ConfiguracionesCliente.hc.enviarMensaje("Cliente creo cronometro");

	}

	public void actualizarCronometro(float valor) {
		cronometro.setTiempoJuego(valor);
	}

	public void actualizarPuntosVidaHUD(int idJugador, float puntos, int vida, int energia) {
		if (Utiles.crearHud) {
			hud.actualizarPuntosVidaHUD(idJugador, puntos, vida, energia);
		}

	}

	public boolean isSetDown1() {
		return setDown1;
	}

	public void setSetDown1(boolean valor, int jugador) {
		setDown1 = valor;
	}

	public boolean isSetRight1() {
		return setRight1;
	}

	public void setSetRight1(boolean valor, int jugador, float velocidadX) {
		setRight1 = valor;
		tecla = "derecha";
		moverPersonaje(tecla, jugador, velocidadX, 0);
	}

	public void setSetRight2(boolean valor, int jugador, float velocidadX) {
		setRight2 = valor;
		tecla = "derecha";
		moverPersonaje(tecla, jugador, velocidadX, 0);
	}

	public void setShift1(boolean valor, int idJugador, float velocidadModif, float energiaModif) {
		setShift1 = valor;
		jugador1.personaje.setSprinteo(true);
		tecla = "shift";
		moverPersonaje(tecla, 1, velocidadModif, energiaModif);
	}

	public void setShift2(boolean valor, int idJugador, float velocidadModif, float energiaModif) {
		setShift2 = valor;

		tecla = "shift";
		jugador2.personaje.setSprinteo(true);
		moverPersonaje(tecla, 2, velocidadModif, energiaModif);

	}

	private void moverPersonaje(String tecla, int jugador, float velocidad, float energia) {
		if (jugador == 1) {
			jugador1.personaje.setVelocidadY(velocidad);
			jugador1.personaje.setEnergiaSalto(energia);

			jugador1.personaje.moverPersonaje(tecla, 1, velocidad, energia);

		}
		if (jugador == 2) {
			jugador2.personaje.setVelocidadY(velocidad);
			jugador2.personaje.setEnergiaSalto(energia);
			jugador2.personaje.moverPersonaje(tecla, 2, velocidad, energia);

		}

	}

	public boolean isSetLeft1() {
		return setLeft1;
	}

	public void setSetLeft1(boolean valor, int jugador, float velocidadX) {
		setLeft1 = valor;
		tecla = "izquierda";
		moverPersonaje(tecla, jugador, velocidadX, 0);
	}

	public void setSetLeft2(boolean valor, int jugador, float velocidadX) {
		setLeft2 = valor;
		tecla = "izquierda";
		moverPersonaje(tecla, jugador, velocidadX, 0);
	}

	public boolean isSetUp1() {
		return setUp1;
	}

	public void sumarPuntos(int idJugador, float puntuacion) {
		if (idJugador == 1) {
			jugador1.sumarPuntos(puntuacion);

		}
		if (idJugador == 2) {
			jugador2.sumarPuntos(puntuacion);
		}

	}

	public void setSetUp1(boolean valor, int jugador) {
		this.setUp1 = valor;
	}

	public boolean isSetDown2() {
		return setDown2;
	}

	public void setSetDown2(boolean valor, int jugador) {
		setDown2 = valor;
	}

	public void setSpace1(boolean valor, int jugador, float velocidadY, float energiaSalto) {
		setSpace1 = valor;
		tecla = "space";
		moverPersonaje(tecla, jugador, velocidadY, energiaSalto);
		// actualizarCamara(jugador);
	}

	public void setSpace2(boolean valor, int jugador, float velocidadY, float energiaSalto) {

		setSpace2 = valor;
		tecla = "space";
		moverPersonaje(tecla, jugador, velocidadY, energiaSalto);
		// actualizarCamara(jugador);

	}

	public boolean isSetRight2() {
		return setRight2;
	}

	public void setSetRight2(boolean valor, int jugador) {
		setRight2 = valor;
	}

	public boolean isSetLeft2() {
		return setLeft2;
	}

	public void setSetLeft2(boolean valor, int jugador) {
		setLeft2 = valor;
	}

	public boolean isSetUp2() {
		return setUp2;
	}

	public void setSetUp2(boolean valor, int jugador) {
		setUp2 = valor;
	}

	@Override
	public void resize(int width, int height) {

		if (ConfiguracionesCliente.crearCamara >= 1) {
			camara.getGamePort().update(width, height);
		}

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		this.dispose();

	}

	public void crearCamara() {
		camara = new Camara();
		ConfiguracionesCliente.crearCamara++;
		System.out.println(ConfiguracionesCliente.crearCamara++);

	}

	public void crearMapa() {
		mapa = new Mapa();
		crearCoaliciones();

	}

	public void actualizarPosCamY(float posY) {
		this.camara.getCamaraJuego().position.y = posY;
		ConfiguracionesCliente.verificarVida = true; // ESTE BOOLEANO informa que, como el jugador ya salto una vez,
														// entonces la camara sube sola. Al iniciar, la camara está un
														// poco más arriba del jugador
		// por lo tanto iba a sacarle la vida SIEMPRE que empezaba el juego. Por lo
		// tanto, que SOLO recien empiece
		// a sacar vida, una vez que YA SE AJUSTO LA CAMARA AUTOMATICAMENTE y empieza a
		// subir sola.

		// Conclusión -> Comenzará a sacar vida a los personajes recien que UNA VEZ
		// SALTA ALGUNO y la camara los come
		// desde ese instante.

	}

	public void setVidaPersonaje(int nroJugador, float vida) {
		if (nroJugador == 1) {
			jugador1.getPersonajeJugador().setVida(vida);

		}
		if (nroJugador == 2) {
			jugador2.getPersonajeJugador().setVida(vida);
		}

	}

	public void finJuego(String opcion) {
		final String opcion2 = opcion;
		
		//personaje.b2Cuerpo.getPosition().y = 20.24f;

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {

				camara.getCamaraJuego().zoom = 1;

				Render.app.setScreen(new PantallaFin(sb, opcion2));
			}
		});

	}

	public void aumentarEnergiaPersonaje(int idJugador, float energiaSalto) {
		if (idJugador == 1) {
			jugador1.personaje.setEnergiaSalto(energiaSalto);

		} else if (idJugador == 2) {

			jugador2.personaje.setEnergiaSalto(energiaSalto);

		}

	}

	public void setVelocidadY(int idJugador, float velocidadYModif) {
		if (idJugador == 1) {
			jugador1.personaje.setVelocidadY(velocidadYModif);
		}
		if (idJugador == 2) {
			jugador2.personaje.setVelocidadY(velocidadYModif);
		}

	}

	public void destruirMoneda() {
		Utiles.COIN_BIT = Utiles.DESTROYED_BIT;

	}

}
