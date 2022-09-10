package com.dirty.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import com.dirty.jugadores.Jugador1;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.pantallas.PantallaJuego;
import com.dirty.utiles.Utiles;

import configuraciones.ConfiguracionesCliente;

public abstract class Personaje extends Sprite {
	private String nombre;
	private boolean gano;
	public World mundo;
	public Body b2Cuerpo;
	private float x;

	private float y;
	private float ancho;
	private float alto;
	private boolean muerto;
	private static TextureAtlas texturaAtlas;
	private float velocidadX = 5;
	private float velocidadNormal = 5;
	private float velocidadY; // Velocidad se rige por variable que le manda el servidor una vez que el
	// servidor hace saltar al cliente.
	private TextureRegion region;
	private Texture textura;
	BodyDef bdef;
	private float vida=1000;
	private float vidaInicial;
	private int i = 0;
	private float max = 0;
	private float altura = 0;
	private float energia = 0;
	private float min = 0;
	private float energiaInicial;
	private float energiaSalto;
	private float velocidadXSprint = 8;
	private FixtureDef fdef = new FixtureDef();
	private boolean sprinteo = false;

	public Personaje(String nombre, int puntos, float x, float y, float ancho, float alto, boolean muerto, World mundo,
			PantallaJuego pantalla) {
		//super(pantalla.getTextureAtlas().findRegion("BATMAN_ANIMACION"));
		this.nombre = nombre;
		this.puntos = puntos;
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.muerto = muerto;

		this.mundo = mundo;

		crearPersonaje();

		//crearTextureRegion(pantalla.getTextureAtlas());

	}

	public float getEnergia() {
		return energia;
	}

	public void setEnergia(float energia, boolean reduceEnergia) {
		if (reduceEnergia) {
			this.energia -= energia;
		} else if (this.energia < this.energiaInicial && !reduceEnergia) {
			this.energia += energia;
			if (this.energia >= energiaInicial) { // x si suma de A GOLPE 200 unidades la tenemos que limitar a la
													// inicial (maxima).
				this.energia = energiaInicial;
				// System.out.println("Energia maxima");
			}

		} else if (this.energia <= 0) {
			this.energia = 0;
		}

	}

	public void setGano(boolean gano) {
		this.gano = gano;
		if (gano == true) {
			Utiles.valor = "GANASTE";

		} else {

			Utiles.valor = "PERDISTE";
		}
	}

	public boolean isGano() {
		return gano;
	}

	public float getEnergiaInicial() {
		return energiaInicial;
	}

	public void setEnergiaInicial(float energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

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

	public float getVida() {
		return vida;
	}

	public void setVida(float vida) {
		this.vida = vida;
		if (this.vida <= 0) {
			this.gano = false;
			ConfiguracionesCliente.hc.enviarMensaje(("Fin juego P1/P2"));
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

	private void crearTextureRegion(TextureAtlas atlas) {
		region = new TextureRegion(getTexture(), 0, 0, 300, 300);
		setBounds(0, 0, 300 / Utiles.PPM, 300 / Utiles.PPM); // tercera columna negativo da vuelta el sprite
		setRegion(region);
		setPosition((b2Cuerpo.getPosition().x - (getWidth() / 2f)), (b2Cuerpo.getPosition().y - (getHeight() / 2f)));

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

	public void moverPersonaje(String tecla, int jugador, float velocidad, float energia) {

		if (tecla.equals("shift")) {

			if (jugador == 1) {

				if (this.energia > 70 && sprinteo == true) {
					this.velocidadX = velocidad;

					if (this.velocidadX >= this.velocidadXSprint) {
						velocidad = this.velocidadX;
						setEnergia(energia, true);

						// System.out.println("Energia actual " +this.getEnergia());

					}
				}

			}
			if (jugador == 2) {

				if (this.energia > 70 && sprinteo == true) {
					this.velocidadX = velocidad;

					if (this.velocidadX >= this.velocidadXSprint) {
						velocidad = this.velocidadX;

						setEnergia(energia, true);

						// System.out.println("Energia actual " +this.getEnergia());

					}

				}

			}
		}
		if (tecla.equals("derecha")) {

			this.b2Cuerpo.applyLinearImpulse(new Vector2(velocidad, 0), this.b2Cuerpo.getWorldCenter(), true);
			// se hace referencia a un solo cuerpo, porque se envia el mensaje desde
			// cualquier personaje que se haya accionado la accion
			// desde PANTALLAJUEGO (setRight1, setRight2, si el personaje == 1 entonces va a
			// personaje, si es el 2 va a personaje2)

		}
		if (tecla.equals("izquierda")) {
			this.b2Cuerpo.applyLinearImpulse(new Vector2(velocidad, 0), this.b2Cuerpo.getWorldCenter(), true);

		}
		if (tecla.equals("space")) {

			this.b2Cuerpo.applyLinearImpulse(new Vector2(0, this.velocidadY), this.b2Cuerpo.getWorldCenter(), true);
		} else {
			this.b2Cuerpo.applyLinearImpulse(new Vector2(0, 0), this.b2Cuerpo.getWorldCenter(), true);
		}

		/*
		 * if (Gdx.input.isKeyPressed(Input.Keys.A) &&
		 * this.b2Cuerpo.getLinearVelocity().x >= -this.getVelocidadX()) {
		 * 
		 * this.b2Cuerpo.applyLinearImpulse(new Vector2(-velocidadX, 0f),
		 * this.b2Cuerpo.getWorldCenter(), true);
		 * 
		 * } else
		 */

		/*
		 * if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { //
		 * this.b2Cuerpo.applyLinearImpulse(new Vector2(0,40f), //
		 * this.b2Cuerpo.getWorldCenter(), true); this.b2Cuerpo.applyLinearImpulse(new
		 * Vector2(0, velocidadY), this.b2Cuerpo.getWorldCenter(), true); VELOCIDADY
		 * TENIA EL VALOR DE 10 PARA CADA CLIENTE
		 * 
		 * } else {
		 * 
		 * this.b2Cuerpo.applyLinearImpulse(new Vector2(0, 0),
		 * this.b2Cuerpo.getWorldCenter(), true);
		 * 
		 * }
		 */

	}

	public void setVelocidadY(float velocidadY) {
		if (velocidadY > 10) {
			this.velocidadY = 10;
		} else {
			this.velocidadY = velocidadY;
		}

	}

	public void setEnergiaSalto(float energiaSalto) {
		if (energiaSalto <= 0) {
			this.energiaSalto = 0;
		} else {
			this.energiaSalto = energiaSalto;
		}

	}

	public float getEnergiaSalto() {
		return energiaSalto;
	}

	private void crearPersonaje() {
		bdef = new BodyDef();
		bdef.position.set(1435 / Utiles.PPM, 300 / Utiles.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2Cuerpo = mundo.createBody(bdef);

		CircleShape shape = new CircleShape();
		shape.setRadius(19 / Utiles.PPM);
		fdef.shape = shape;
		
		//Le asignamos a fixture los Bits
		fdef.filter.categoryBits= Utiles.PERSONAJE_BIT;
		
		//Puede chocar con:
		fdef.filter.maskBits=(short) (Utiles.COIN_BIT | Utiles.DEFAULT_BIT | Utiles.VIDA_BIT);
		// que la cabeza tenga una especie de sensor que dispare una accion al tocar
		// algo

	}

	public BodyDef getBodyDef() {
		return this.bdef;
	}

	public void definirFixtura(String fixtura) {

		b2Cuerpo.createFixture(fdef);
		fdef.isSensor = true;
		b2Cuerpo.setUserData(fixtura); // se setea que el body es de tipo cuerpo
		b2Cuerpo.createFixture(fdef).setUserData(fixtura); // se crea el fixture del cuerpo y se le asigna el userdata.
	}

	public void update(float dt) {

		// super.translate(b2Cuerpo.getPosition().x - (region.getTexture().getHeight() +
		// (region.getTexture().getWidth() / 2.5f)),
		// b2Cuerpo.getPosition().y - (region.getTexture().getHeight() / 100));
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
