package com.dirty.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dirty.configuraciones.ConfiguracionesSERVER;
import com.dirty.jugadores.Jugador1SERVER;
import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.pantallas.PantallaJuegoSERVER;

import com.dirty.utiles.UtilesSERVER;

public abstract class PersonajeSERVER extends Sprite {
	private String nombre;

	public World mundo;
	public Body b2Cuerpo;
	private boolean gano;
	private float energiaSalto = 100;

	public float getEnergia() {
		return energia;
	}

	public float setEnergia(float energia, boolean reduceEnergia) {
		if (reduceEnergia) {
			this.energia -= energia;
			return this.energia;
		} else if (this.energia < this.energiaInicial && !reduceEnergia) {
			this.energia += energia;

			if (this.energia >= energiaInicial) { // x si suma de A GOLPE 200 unidades la tenemos que limitar a la
													// inicial (maxima).
				this.energia = energiaInicial;
				return this.energia;
				// System.out.println("Energia maxima");
			}
			return this.energia;

		} else if (this.energia <= 0) {
			this.energia = 0;
			return this.energia;
		}

		return this.energia;
	}

	public boolean isGano() {
		return gano;
	}

	public void setGano(boolean gano) {
		this.gano = gano;
		if (gano == true) {
			UtilesSERVER.valor = "GANASTE";
		} else {
			UtilesSERVER.valor = "PERDISTE";
		}
	}

	public float getEnergiaInicial() {
		return energiaInicial;
	}

	public void setEnergiaInicial(float energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	private float x;

	private float y;
	private float ancho;
	private float alto;
	private boolean muerto;
	private static TextureAtlas texturaAtlas;
	private float velocidadX = 5;
	private float velocidadNormal = 5;

	public float getVelocidadNormal() {
		return velocidadNormal;
	}

	public void setVelocidadNormal(float velocidadNormal) {
		this.velocidadNormal = velocidadNormal;
	}

	public void setVelocidadX(float velocidadX) {
		this.velocidadX = velocidadX;
		if (this.velocidadX <= this.velocidadNormal) {
			this.velocidadX = velocidadNormal;
		}
	}

	public void reducirVelocidadX(float velocidadX) {
		this.velocidadX -= velocidadX;
	}

	private float velocidadY = 10;
	private TextureRegion region;
	private Texture textura;
	BodyDef bdef;
	private float vida = 1000;
	private float vidaInicial;
	private int i = 0;
	private float max = 0;
	private FixtureDef fdef = new FixtureDef();
	private float altura = 0;
	private float energia = 0;
	private float min = 0;
	private float energiaInicial;
	private float velocidadXSprint = 8;

	private boolean sprinteo = false;

	public float getVida() {
		return vida;
	}

	public void setVida(float vida) {
		this.vida = vida; // como esl ap rimera vez y no sirve para verificar asignamos de una.
		ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Vida/P1/" + this.vida);
		ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Vida/P2/" + this.vida);

	}

	public void setVida(float vida, boolean restaVida, int idJugador) {
		if (this.vida < 0) {
			this.vida = 0;
			this.gano = false;

		}
		if (restaVida) {
			this.vida -= vida;
		} else if (!restaVida) {
			if (this.vida >= 100) {
				this.vida = 100;
			} else {
				this.vida += vida;
			}

		}
		if (idJugador == 1) {

			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Vida/P1/" + this.vida);
			
		}

		if (idJugador == 2) {
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Vida/P2/" + this.vida);
			
		}

	}

	public float getEnergiaSalto() {
		return energiaSalto;
	}

	public void setEnergiaSalto(int idJugador, float energiaSalto, boolean aumento) {
		float velocidadYModif = 0;
		if (this.energiaSalto <= 0) {
			this.energiaSalto = 0;
		}
		if (aumento) {
			this.energiaSalto += 100;
			velocidadYModif = setVelocidadY(10f, false);

		}

		if (this.energiaSalto > 100) {
			this.energiaSalto = 100;
		}

		if (idJugador == 1) {

			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/EnergiaSalto/P1/" + this.energiaSalto);
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Velocidad/Y/P1/" + velocidadYModif);

		}
		if (idJugador == 2) {
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/EnergiaSalto/P2/" + this.energiaSalto);
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Velocidad/Y/P2/" + velocidadYModif);
		}

	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getAlturaMinima() {
		return min;
	}

	public void setAlturaMaxima(float min) {
		this.min = min;
	}

	public float getVidaInicial() {
		return vidaInicial;
	}

	public void setVidaInicial(int vidaInicial) {
		this.vidaInicial = vidaInicial;
	}

	public boolean isSprinteo() {
		return sprinteo;
	}

	public void setSprinteo(boolean sprinteo) {
		this.sprinteo = sprinteo;
	}

	private int puntos;

	public PersonajeSERVER(String nombre, int puntos, float x, float y, float ancho, float alto, boolean muerto,
			World mundo, PantallaJuegoSERVER pantalla) {
		// super(pantalla.getTextureAtlas().findRegion("BATMAN_ANIMACION"));
		this.nombre = nombre;
		this.puntos = puntos;
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.muerto = muerto;

		this.mundo = mundo;

		crearPersonaje();

		// crearTextureRegion(pantalla.getTextureAtlas());

	}

	private void crearTextureRegion(TextureAtlas atlas) {
		region = new TextureRegion(getTexture(), 0, 0, 100, 100);
		setBounds(0, 0, 100, 100); // tercera columna negativo da vuelta el sprite

		setRegion(region);

	}

	public float getVelocidadX() {
		return velocidadX;
	}

	public String getNombre() {
		return nombre;
	}

	public static TextureAtlas getAtlas() {
		return texturaAtlas;
	}

	public int getPuntos() {
		return puntos;
	}

	public void moverPersonaje(String tecla, Integer jugador) {
		float energiaSeteada = 0;

		if (tecla.equals("shift")) {

			if (jugador == 1) {
				if (this.energia > 70 && sprinteo == true) {

					this.velocidadX += 3;

					if (this.velocidadX > this.velocidadXSprint) {
						this.velocidadX = this.velocidadXSprint;
						energiaSeteada = setEnergia(0.3f, true);

						// System.out.println("Energia actual " +this.getEnergia());

					}
				}

				ConfiguracionesSERVER.hs
						.enviarMensajeParaTodos("Actualizar/Velocidad/P1/" + this.velocidadX + energiaSeteada); //Esto esta mal, no lo hice por un problema en el sprint que no pude solucionar por como se juega
				//el juego

			}
			if (jugador == 2) {

				if (this.energia > 70 && sprinteo == true) {
					this.velocidadX += 3;

					if (this.velocidadX > this.velocidadXSprint) {
						this.velocidadX = this.velocidadXSprint;
						energiaSeteada = setEnergia(0.3f, true);
//
//						// System.out.println("Energia actual " +this.getEnergia());
//
					}
				}
				ConfiguracionesSERVER.hs
						.enviarMensajeParaTodos("Actualizar/Velocidad/P2/" + this.velocidadX + energiaSeteada);

			}

		}

		if (tecla.equals("derecha")) {
			if (velocidadX > velocidadNormal && sprinteo == false) {
				velocidadX = this.velocidadNormal;

			}

			this.b2Cuerpo.applyLinearImpulse(new Vector2(velocidadX, 0), this.b2Cuerpo.getWorldCenter(), true);
			if (jugador == 1) {

				ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Posicion/P1/Derecha/" + velocidadX);
			}
			if (jugador == 2) {
				ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Posicion/P2/Derecha/" + velocidadX);
			}

		}
		if (tecla.equals("izquierda")) {
			if (velocidadX > velocidadNormal && sprinteo == false) {
				velocidadX = this.velocidadNormal;

			}
			this.b2Cuerpo.applyLinearImpulse(new Vector2(-velocidadX, 0f), this.b2Cuerpo.getWorldCenter(), true);
			if (jugador == 1) {
				ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Posicion/P1/Izquierda/" + velocidadX);
			}
			if (jugador == 2) {
				ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Posicion/P2/Izquierda/" + velocidadX);
			}
		}

		if (tecla.equals("space")) {

			float velocidadYModif = 0;

			this.b2Cuerpo.applyLinearImpulse(new Vector2(0, velocidadY), this.b2Cuerpo.getWorldCenter(), true);
			if (jugador == 1) {
				if (this.energiaSalto > 0) {
					setEnergiaSalto(10f, true);
				} else {
					setEnergiaSalto(00f, true);
				}
				if (energiaSalto <= 100) {
					velocidadYModif = setVelocidadY(1f, true);

				}

				ConfiguracionesSERVER.hs
						.enviarMensajeParaTodos("Actualizar/Posicion/P1/Salto/" + velocidadYModif + "/" + energiaSalto);
			}
			if (jugador == 2) {
				if (this.energiaSalto > 0) {
					setEnergiaSalto(10f, true);
				} else {
					setEnergiaSalto(0f, true);
				}

				if (energiaSalto <= 100) {
					velocidadYModif = setVelocidadY(1f, true);

				}

				ConfiguracionesSERVER.hs
						.enviarMensajeParaTodos("Actualizar/Posicion/P2/Salto/" + velocidadYModif + "/" + energiaSalto);
			}

		} else {
			this.b2Cuerpo.applyLinearImpulse(new Vector2(0, 0), this.b2Cuerpo.getWorldCenter(), true);
		}

		// if (Gdx.input.isKeyPressed(Input.Keys.A) &&
		// this.b2Cuerpo.getLinearVelocity().x >= -this.getVelocidadX()) {

		// }
		/*
		 * if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { //
		 * this.b2Cuerpo.applyLinearImpulse(new Vector2(0,40f), //
		 * this.b2Cuerpo.getWorldCenter(), true); this.b2Cuerpo.applyLinearImpulse(new
		 * Vector2(0, velocidadY), this.b2Cuerpo.getWorldCenter(), true);
		 * 
		 * } else {
		 * 
		 * this.b2Cuerpo.applyLinearImpulse(new Vector2(0, 0),
		 * this.b2Cuerpo.getWorldCenter(), true);
		 * 
		 * }
		 */

	}

	public void setEnergiaSalto(float energiaSalto, boolean reducir) {
		if (this.energiaSalto <= 0) {
			this.energiaSalto = 0;
			this.velocidadY = 0;
		}
		if (reducir && energiaSalto > 0) {
			this.energiaSalto -= energiaSalto;
		}
		if (this.energiaSalto > 100) {
			this.energiaSalto = 100;
		}

	}

	public float setVelocidadY(float velocidadYRestar, boolean reducir) {

		if (this.velocidadY <= 0) {
			this.velocidadY = 0;

		}
		if (reducir && this.velocidadY > 0) {
			this.velocidadY -= velocidadYRestar;

			return this.velocidadY;
		}
		if (this.velocidadY > 10) {
			this.velocidadY = 10;
			return this.velocidadY;

		}
		if (!reducir) {
			this.velocidadY += velocidadYRestar;
			return this.velocidadY;
		}

		return this.velocidadY;
	}

	private void crearPersonaje() {
		bdef = new BodyDef();
		bdef.position.set(1435 / UtilesSERVER.PPM, 300 / UtilesSERVER.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2Cuerpo = mundo.createBody(bdef);

		CircleShape shape = new CircleShape();
		shape.setRadius(19 / UtilesSERVER.PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = UtilesSERVER.PERSONAJE_BIT;

		// Puede chocar con:
		fdef.filter.maskBits = UtilesSERVER.COIN_BIT | UtilesSERVER.DEFAULT_BIT | UtilesSERVER.VIDA_BIT;
		b2Cuerpo.createFixture(fdef);
		fdef.isSensor = true; // que la cabeza tenga una especie de sensor que dispare una accion al tocar
								// algo

	}

	public void definirFixtura(String fixtura) {
//		b2Cuerpo.setUserData("cuerpo"); // se setea que el body es de tipo cuerpo
//		b2Cuerpo.createFixture(fdef).setUserData("cuerpo"); // se crea el fixture del cuerpo y se le asigna el userdata.
		b2Cuerpo.createFixture(fdef);
		fdef.isSensor = true;
		b2Cuerpo.setUserData(fixtura); // se setea que el body es de tipo cuerpo
		b2Cuerpo.createFixture(fdef).setUserData(fixtura); // se crea el fixture del cuerpo y se le asigna el userdata.
	}

	public BodyDef getBodyDef() {
		return this.bdef;
	}

	public void update(float dt) {

		setPosition((b2Cuerpo.getPosition().x - getWidth() / 5) - 960, (b2Cuerpo.getPosition().y + getHeight()) - 5);
		// super.translate(b2Cuerpo.getPosition().x - (region.getTexture().getHeight() +
		// (region.getTexture().getWidth() / 2.5f)),
		// b2Cuerpo.getPosition().y - (region.getTexture().getHeight() / 100));
	}

	public void setAltura(JugadorBASESERVER jugador) {

		altura = jugador.getPersonajeJugador().b2Cuerpo.getPosition().y;
		if (i == 0) {
			max = altura;
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Altura/Max/"
			// +max);
			min = altura;
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Altura/Min/"
			// +min);
			i++;
			// ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Actualizar/Altura/ValorI"
			// +i);

			jugador.sumarPuntos(1f);

		}

		if (altura > max) {
			max = altura;

			jugador.sumarPuntos(1f);
		}

		if (altura < max) {
			min = altura; // Si la altura actual es MENOR QUE LA MAXIMA, entonces se coloca que el
							// personaje est� perdiendo puntos y se guarda nuevamente la altura como
							// minima
							// en caso de que sea M�S CCHICA TODAVIA
			jugador.sumarPuntos(-0.03f); // Restamos los puntos ya que CAY� A UNA ALTURA MENOR QUE LA MAXIMA y
											// entonces, la camara est� haciendo esfuerzos por seguirlo
		}

	}

	public float getAlturaMaxima() {

		return this.max;
	}

	public float getVelocidadXSprint() {
		return velocidadXSprint;
	}

	public void setVelocidadXSprint(float velocidadXSprint) {
		this.velocidadXSprint = velocidadXSprint;
	}

}
