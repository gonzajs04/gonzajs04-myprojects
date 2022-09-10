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
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.camara.CamaraSERVER;
import com.dirty.configuraciones.ConfiguracionesSERVER;
import com.dirty.configuraciones.ConfiguracionesSERVIDOR;
import com.dirty.elementos.TextoSERVER;
import com.dirty.hilos.CronometroSERVER;
import com.dirty.hilos.HiloTiempoSERVER;
import com.dirty.interfaz.HudSERVER;
import com.dirty.juego.JumpingTOWERRED;
import com.dirty.jugadores.Jugador1SERVER;
import com.dirty.jugadores.Jugador2SERVER;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.mapas.MapaSERVER;
import com.dirty.personajes.BatmanSERVER;
import com.dirty.personajes.GokuSERVER;
import com.dirty.personajes.PersonajeSERVER;
import com.dirty.red.HiloServidor;

import com.dirty.utiles.B2WorldCreatorSERVER;
import com.dirty.utiles.RecursosSERVER;

import com.dirty.utiles.RenderSERVER;

import com.dirty.utiles.UtilesSERVER;
import com.dirty.utiles.WorldContactListenerSERVER;
import com.dirty.utiles.aumentarEnergiaSERVER;
import com.dirty.utiles.reducirVelocidadSERVER;
import com.dirty.utiles.subirCamaraSERVER;

public class PantallaJuegoSERVER implements Screen {

	private JumpingTOWERRED game;
	Texture textura;

	private MapaSERVER mapa;
	private Sprite sprite;
	private UtilesSERVER utiles = new UtilesSERVER();
	private SpriteBatch sb;
	private World mundo;
	private float valorCamY;
	private CamaraSERVER camara;
	private float valorCamX;
	private HudSERVER hud;
	private HiloTiempoSERVER hilos;
	private Box2DDebugRenderer b2dr;
	private Rectangle rect;
	private Body cuerpo;
	private PersonajeSERVER personaje;
	private PersonajeSERVER personaje2;
	private TextureAtlas textureAtlas;
	private CronometroSERVER cronometro;
	private Jugador1SERVER jugador1;
	private Jugador2SERVER jugador2;
	private JugadorBASESERVER jugadorBASE;
	private TextoSERVER esperandoClientes;
	private reducirVelocidadSERVER rv;
	private aumentarEnergiaSERVER ae;
	private boolean setDown1;
	private boolean setRight1;
	private boolean setLeft1;
	private boolean setUp1;
	private boolean setDown2;
	private boolean setRight2;
	private boolean setLeft2;
	private boolean setUp2;
	private boolean setShift1;
	private boolean setShift2;
	private boolean setSpace1;
	private boolean setSpace2;
	private WorldContactListenerSERVER worldContactListener;
	private B2WorldCreatorSERVER b2WorldCreator;
	private String tecla;
	String textos[] = { "JUGADOR", "PUNTUACION", "VIDA", "TIEMPO" };
	TextoSERVER opciones[] = new TextoSERVER[7];

	public PantallaJuegoSERVER(JumpingTOWERRED game) {
		RenderSERVER.limpiarPantalla();
		this.game = game;
		this.sb = utiles.getSb();
		// Hosteo el server
		ConfiguracionesSERVER.hs = new HiloServidor(this);
		// Starteo el server y creo los dos jugadores y espero que cada uno ingrese y
		// tome uno.
		ConfiguracionesSERVER.hs.start();

		// System.out.println("El personaje del jugador " +jugador1.getNombre() + " es "
		// +jugador1.getPersonajeJugador().getNombre());
		// sprite=textureAtlas.createSprite("BATMAN_ANIMACION");
		// sprite.setPosition(personaje.b2Cuerpo.getPosition().x-1000,
		// personaje.b2Cuerpo.getPosition().y);

	}

	private void crearCronometro() {

		cronometro = new CronometroSERVER();
		hilos = new HiloTiempoSERVER(cronometro, jugador1, jugador2);
		hilos.start();
		ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Crea/Cronometro/" + cronometro.getTiempoJuego());

	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	private void crearMundo() {
		mundo = new World(new Vector2(0, (-9.8f * 2)), true);
		b2dr = new Box2DDebugRenderer();

	}

	private void crearCoaliciones() {

		mundo.setContactListener(worldContactListener = new WorldContactListenerSERVER(personaje));
		b2WorldCreator = new B2WorldCreatorSERVER(mundo, mapa.getMapa(), mapa);

		worldContactListener.setJugador(jugador1, 0);
		worldContactListener.setJugador(jugador2, 1);
		b2WorldCreator = new B2WorldCreatorSERVER(mundo, mapa.getMapa(), mapa);
		b2WorldCreator.setJugador1(jugador1);
		b2WorldCreator.setJugador2(jugador2);
		b2WorldCreator.crearCoaliciones(jugador1);
		b2WorldCreator.crearCoaliciones(jugador2);
	}

	private void setMundo(World mundo) {
		this.mundo = mundo;

	}

	private World getMundo() {
		return this.mundo;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Si no empez� el online entonces que se muestre un MENSAJE QUE DIGA QUE
		// EST� ESPERANDO CLIENTES
		if (!ConfiguracionesSERVIDOR.empiezaOnlineC) {
			esperandoClientes = new TextoSERVER(RecursosSERVER.FUENTEMENU, 34, Color.YELLOW, true);
			esperandoClientes.setTexto("PARTIDA HOSTEADA, ESPERANDO CLIENTES");
			esperandoClientes.setPosition((UtilesSERVER.vpWidth / 2) - 255, (UtilesSERVER.vpHeight) - 40);
			esperandoClientes.dibujar();
		} else {

			crearItems();
			if (ConfiguracionesSERVIDOR.dispose) {
				this.dispose();
				ConfiguracionesSERVIDOR.dispose = false;
			}

			if (!UtilesSERVER.crearCronometro) {
				crearCronometro();
				UtilesSERVER.crearCronometro = true;
			}

			update(delta);
			// Colorfondo
			if (UtilesSERVER.yaSalte == true) {
				subirCamaraAutomaticamente(); 
			}

			// Renderizar box2d
			mapa.getRenderizarMapa().render();
			b2dr.render(mundo, camara.getCamaraJuego().combined);

			sb.end();
			hud.getStage().draw();
			sb.begin();
			// personaje.draw(game.sb);

			// sprite.draw(sb);

			sb.setProjectionMatrix(camara.getCamaraJuego().combined);

			// sb.setProjectionMatrix(hud.getStage().getCamera().combined);
			if (!UtilesSERVER.cambiarZoomCam) {
				camara.getCamaraJuego().zoom = 2.3f;
				ConfiguracionesSERVER.hs
						.enviarMensajeParaTodos("Actualizar/Camara/Zoom/" + camara.getCamaraJuego().zoom);
				UtilesSERVER.cambiarZoomCam = true;
			}

		}

	}

	private void crearItems() {
		if (!ConfiguracionesSERVIDOR.crearItems) {

			crearJugadores();
			crearCamara();
			// textureAtlas = new TextureAtlas("batman.pack");

			crearHud();

			crearMundo();
			setMundo(mundo);
			crearPersonajes();
			setearPersonajes();
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Asigna/Personaje/Jugador");
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Asigna/Personaje/Fixture");

			rv = new reducirVelocidadSERVER();
			ae = new aumentarEnergiaSERVER();
			UtilesSERVER.rvListener = rv;
			UtilesSERVER.aeListener = ae;
			mapa = new MapaSERVER();
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Crea/Mapa");
			crearCoaliciones();
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Crea/Coaliciones");
			ConfiguracionesSERVIDOR.crearItems = true;

		}

	}

	private void setearPersonajes() {
		jugador1.setPersonajeJugador(personaje);
		jugador2.setPersonajeJugador(personaje2);
		definirFixtures();

	}

	private void definirFixtures() {
		jugador1.getPersonajeJugador().definirFixtura("cuerpo");
		jugador2.getPersonajeJugador().definirFixtura("cuerpo2");

	}

	private void crearPersonajes() {
		personaje = new BatmanSERVER(mundo, this);
		personaje2 = new GokuSERVER(mundo, this);

	}

	private void crearHud() {
		hud = new HudSERVER();

	}

	private void crearCamara() {
		camara = new CamaraSERVER();

		ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Crea/Camara"); 
		ConfiguracionesSERVIDOR.camaraCreada = true;
		resize(1280, 720);

	}

	public void crearJugadores() {
		jugador1 = new Jugador1SERVER("Player 1");
	
		jugador2 = new Jugador2SERVER("Player 2");

	}

	public void update(float dt) {

		personaje.update(dt);
		mundo.step(1 / 60f, 6, 2);

		if (!ConfiguracionesSERVIDOR.primeraVezCam) {
			if (jugador1.getPersonajeJugador().b2Cuerpo.getPosition().x > 7
					&& jugador1.getPersonajeJugador().b2Cuerpo.getPosition().x < 29) { // Si el personaje tiene una
																						// posici�n la cual la camara
																						// ve fuera del mapa, entonces
																						// que NO siga moviendo la
																						// camara con respecto al
																						// movimiento del personaje sino
																						// que se fije
				camara.getCamaraJuego().position.x = jugador1.getPersonajeJugador().b2Cuerpo.getPosition().x + 2;
				camara.getCamaraJuego().position.y = jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y + 4.5f;

				ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Inicializa/Posicion/Camara/"
						+ camara.getCamaraJuego().position.x + "/" + camara.getCamaraJuego().position.y);
			}
			ConfiguracionesSERVIDOR.primeraVezCam = true;

		}

		if (ConfiguracionesSERVIDOR.camSubeAutomaticamentePrimeraVez) { // Si ya subio automaticamente por primera vez
																		// porque un cliente salto entonces que empiece
																		// a subir solo
			if (jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y < 72) {
				camara.getCamaraJuego().position.y += UtilesSERVER.valorUnico;
				ConfiguracionesSERVER.hs
						.enviarMensajeParaTodos("Actualizar/Camara/PosY/" + camara.getCamaraJuego().position.y);
				ConfiguracionesSERVIDOR.camSubeAutomaticamentePrimeraVez = false;
			}

			/*
			 * if(jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y>camara.
			 * getCamaraJuego().position.y) {
			 * if(jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y>720) //Si el
			 * jugador est� a una altura MAYOR A LA DEL CIELO {
			 * camara.getCamaraJuego().position.y =
			 * jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y -50;
			 * 
			 * }
			 * 
			 * camara.getCamaraJuego().position.y =
			 * jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y; //Si el jugador
			 * est� a una altura MAYOR A LA CAMARA AUN CUANDO SUBE AUTOAMTICAMENTE
			 * ENTONCES QUE LA CAMARA SIGA AL JUGADOR
			 * 
			 * } else if(jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y<camara.
			 * getCamaraJuego().position.y) // aca deberias hacerl ocon el
			 * jugador2.getPersonaje en el online {
			 * jugador1.getPersonajeJugador().setVida(UtilesSERVER.valorUnico, true);
			 * if(jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y <
			 * jugador1.getPersonajeJugador().getAlturaMinima()) { //Si el jugador CAE de
			 * muy alto, entonces LA CAMARA LO SEGUIR� �A qu� costo? A perder puntos
			 * de vida y puntuacion. Podr� volver a subir y superar el maximo anterior y
			 * la camara seguira subiendo automaticamente pero para eso deber� volver a su
			 * posici�n m�s alta. camara.getCamaraJuego().position.y =
			 * jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y; //La camara SIGUE AL
			 * JUGADOR A COSTE DE DINERO ya que cay� de muy alto. } }
			 * 
			 * 
			 */

		}
		camara.getCamaraJuego().update(); // Se actualiza la camara

		mapa.getRenderizarMapa().setView(camara.getCamaraJuego()); // Se renderiza el mapa

		hud.actualizarDatos(this.cronometro, jugador1, jugador2); // Se actualizan los datos del hud con la info de
																	// ambos jugadores
		controlarPuntajeJugador();

	}

	private void controlarPuntajeJugador() {
		if (jugador1.getPuntos() >= 1500 || jugador2.getPuntos() >= 1500) {
			this.setEstadoVictoria();
		}

	}

	@Override
	public void resize(int width, int height) {

		if (ConfiguracionesSERVIDOR.camaraCreada) {

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

	public void setEstadoVictoria() {

		jugador1.getPersonajeJugador().setGano(true);
		jugador2.getPersonajeJugador().setGano(true);

		this.finJuego();

	}

	public void setDown1(boolean valor, int jugador) {
		setDown1 = valor;
		tecla = "abajo";
		moverPersonaje(tecla, jugador);
	}

	public void setSpace1(boolean valor, int jugador) {
		setSpace1 = valor;
		tecla = "space";
		moverPersonaje(tecla, jugador);
		calcularAlturaMaxima(jugador);
	}

	public void setSpace2(boolean valor, int jugador) {
		setSpace2 = valor;
		tecla = "space";
		moverPersonaje(tecla, jugador);
		calcularAlturaMaxima(jugador);
	}

	public void calcularAlturaMaxima(int idJugador) {
		if (idJugador == 1) {
			jugador1.getPersonajeJugador().setAltura(jugador1); // Se setean los datos maximos y minimos de la altura
																// del personaje en tiempo real
		}
		if (idJugador == 2) {
			jugador2.getPersonajeJugador().setAltura(jugador2);
		}
	}

	public void setRight1(boolean valor, int jugador) {
		setRight1 = valor;
		tecla = "derecha";

		moverPersonaje(tecla, jugador);

	}

	public void setLeft1(boolean valor, int jugador) {
		setLeft1 = valor;
		tecla = "izquierda";

		moverPersonaje(tecla, jugador);

	}

	public void setUp1(boolean valor, int jugador) {
		setUp1 = valor;
		tecla = "arriba";

		moverPersonaje(tecla, jugador);

	}

	public void setDown2(boolean valor, int jugador) {
		setDown2 = valor;
		tecla = "abajo";
		moverPersonaje(tecla, jugador);
	}

	public void setRight2(boolean valor, int jugador) {
		setRight2 = valor;
		tecla = "derecha";

		moverPersonaje(tecla, jugador);

	}

	public void setLeft2(boolean valor, int jugador) {
		setLeft2 = valor;
		tecla = "izquierda";

		moverPersonaje(tecla, jugador);

	}

	public void setUp2(boolean valor, int jugador) {
		setUp2 = valor;
		tecla = "arriba";

		moverPersonaje(tecla, jugador);

	}

	public void setShift1(boolean valor, int idJugador) {
		setShift1 = valor;
		jugador1.personaje.setSprinteo(true);
		tecla = "shift";

		moverPersonaje(tecla, idJugador);
	}

	public void setShift2(boolean valor, int idJugador) {
		setShift2 = valor;
		tecla = "shift";
		jugador2.personaje.setSprinteo(true);
		moverPersonaje(tecla, idJugador);

	}
	/*
	 * public void inicializarCamaraPersonaje(int idJugador) { if (idJugador == 1) {
	 * jugador1.getCamara().getCamaraJuego().position.x =
	 * jugador1.getCamara().getCamaraJuego().position.x;
	 * ConfiguracionesSERVER.hs.enviarMensajeParaTodos(
	 * "Inicializa/Posicion/Camara/P1/" +
	 * jugador1.getCamara().getCamaraJuego().position.x); } if (idJugador == 2) {
	 * jugador2.getCamara().getCamaraJuego().position.x =
	 * jugador2.getCamara().getCamaraJuego().position.x;
	 * ConfiguracionesSERVER.hs.enviarMensajeParaTodos(
	 * "Inicializa/Posicion/Camara/P2/" +
	 * jugador1.getCamara().getCamaraJuego().position.x); } }
	 */

	private void moverPersonaje(String tecla, Integer jugador) {
		if (jugador == 1) {
			jugador1.personaje.moverPersonaje(tecla, jugador);

		}
		if (jugador == 2) {
			jugador2.personaje.moverPersonaje(tecla, jugador);

		}
		// actualizarCamaraJugador(jugador);

	}
	/*
	 * 
	 * private void actualizarCamaraJugador(Integer jugador) {
	 * 
	 * if (jugador == 1) { actualizarCamaraJugador1(); } if (jugador == 2) {
	 * actualizarCamaraJugador2(); }
	 * 
	 * }
	 */
	/*
	 * private void actualizarCamaraJugador1() { if
	 * (jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y < 72) // Si la
	 * cabeza del personaje est� a una {
	 * 
	 * }
	 * 
	 * if (jugador1.getPersonajeJugador().b2Cuerpo .getPosition().y >
	 * jugador1.getCamara().getCamaraJuego().position.y) {
	 * 
	 * jugador1.getCamara().getCamaraJuego().position.y =
	 * jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y; // Si // el
	 * 
	 * } else if (jugador1.getPersonajeJugador().b2Cuerpo .getPosition().y <
	 * jugador1.getCamara().getCamaraJuego().position.y) {
	 * jugador1.getPersonajeJugador().setVida(UtilesSERVER.valorUnico, true); if
	 * (jugador1.getPersonajeJugador().b2Cuerpo.getPosition().y <
	 * jugador1.getPersonajeJugador() .getAlturaMinima()) { // Si el jugador CAE de
	 * muy alto, entonces LA CAMARA LO SEGUIR� �A qu�
	 * 
	 * jugador1.getCamara().getCamaraJuego().position.y =
	 * jugador1.getPersonajeJugador().b2Cuerpo .getPosition().y; // La
	 * 
	 * } }
	 * 
	 * ConfiguracionesSERVER.hs .enviarMensajeParaTodos("Actualizar/Camara/P1/" +
	 * jugador1.getCamara().getCamaraJuego().position.y + "/" +
	 * jugador1.getCamara().getCamaraJuego().position.x);
	 * 
	 * }
	 * 
	 * private void actualizarCamaraJugador2() { if
	 * (jugador2.getPersonajeJugador().b2Cuerpo.getPosition().y < 72) // Si la
	 * cabeza del personaje est� a una {
	 * 
	 * }
	 * 
	 * if (jugador2.getPersonajeJugador().b2Cuerpo .getPosition().y >
	 * jugador2.getCamara().getCamaraJuego().position.y) {
	 * 
	 * jugador2.getCamara().getCamaraJuego().position.y =
	 * jugador2.getPersonajeJugador().b2Cuerpo.getPosition().y; // Si // el
	 * 
	 * } else if (jugador2.getPersonajeJugador().b2Cuerpo .getPosition().y <
	 * jugador2.getCamara().getCamaraJuego().position.y) {
	 * jugador2.getPersonajeJugador().setVida(UtilesSERVER.valorUnico, true); if
	 * (jugador2.getPersonajeJugador().b2Cuerpo.getPosition().y <
	 * jugador2.getPersonajeJugador() .getAlturaMinima()) { // Si el jugador CAE de
	 * muy alto, entonces LA CAMARA LO SEGUIR� �A qu�
	 * 
	 * jugador2.getCamara().getCamaraJuego().position.y =
	 * jugador2.getPersonajeJugador().b2Cuerpo .getPosition().y; // La
	 * 
	 * } } ConfiguracionesSERVER.hs .enviarMensajeParaTodos("Actualizar/Camara/P2/"
	 * + jugador2.getCamara().getCamaraJuego().position.y + "/" +
	 * jugador1.getCamara().getCamaraJuego().position.x); }
	 */

	public void subirCamaraAutomaticamente() {

		if (camara.getCamaraJuego().position.y < 72) {
			camara.getCamaraJuego().position.y += 0.0001f;
			ConfiguracionesSERVIDOR.camSubeAutomaticamentePrimeraVez = true;
			ConfiguracionesSERVER.hs
					.enviarMensajeParaTodos("Actualizar/Camara/PosY/" + camara.getCamaraJuego().position.y);
		}
		// camara.getCamaraJuego().position.y += UtilesSERVER.valorUnico;

	}

	public void restarVida(float cantRestar, boolean b, int nroJugador) {
		

		if (nroJugador == 1) {
			jugador1.getPersonajeJugador().setVida(cantRestar, true, 1);
		}
		if (nroJugador == 2) {
			jugador2.getPersonajeJugador().setVida(cantRestar, true, 2);
		}

	}

	public void finJuego() {
		camara.getCamaraJuego().position.y = 8.24f;
		if (jugador1.getPersonajeJugador().isGano() || jugador2.getPersonajeJugador().isGano()) {
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Fin/Juego/AmbosGanaron");

			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					// process the result, e.g. add it to an Array<Result> field of the
					// ApplicationListener.

					camara.getCamaraJuego().zoom = 1;

					RenderSERVER.app.setScreen(new PantallaFinSERVER(sb, "ambosGanaron"));
				}
			});

		}
		if (!jugador1.getPersonajeJugador().isGano() || !jugador2.getPersonajeJugador().isGano()) {
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Fin/Juego/AmbosPerdieron");
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {

					camara.getCamaraJuego().zoom = 1;
					RenderSERVER.app.setScreen(new PantallaFinSERVER(sb, "ambosPerdieron"));
				}
			});
		}

	}

	public void aumentarPuntos(int idJugador) {
		if (idJugador == 1) {
			jugador1.sumarPuntos(100f);
		
		} else if (idJugador == 2) {
			jugador2.sumarPuntos(100f);
			

		}

	}
	
	
	public void aumentarVida(int idJugador, float vida) {
		
		if(idJugador==1) {
			jugador1.getPersonajeJugador().setVida(vida, false, idJugador);
		}
		
		if(idJugador==2) {
			jugador2.getPersonajeJugador().setVida(vida, false, idJugador);
		}
		
	}

//	public void aumentarEnergiaPersonaje(int idJugador) {
//		if (idJugador == 1) {
//			jugador1.personaje.setEnergiaSalto(idJugador, jugador1.personaje.getEnergiaSalto(), true);
//
//		} else if (idJugador == 2) {
//
//			jugador2.personaje.setEnergiaSalto(idJugador, jugador2.personaje.getEnergiaSalto(), true);
//
//		}
//
//	}
}
