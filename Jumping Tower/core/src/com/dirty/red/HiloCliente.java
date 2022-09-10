package com.dirty.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;
import com.dirty.jugadores.JugadorBASE;
import com.dirty.pantallas.PantallaJuego;
import com.dirty.utiles.Utiles;

import configuraciones.ConfiguracionesCliente;
import configuraciones.ConfiguracionesCliente;

public class HiloCliente extends Thread {

	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puerto = 9999;
	private PantallaJuego pj;
	private int i = 0;

	public HiloCliente(PantallaJuego pj) {
		this.pj = pj;
		ConfiguracionesCliente.fin = false;
		try {
			ipServer = InetAddress.getByName("255.255.255.255");

			conexion = new DatagramSocket();
			i++;
		} catch (SocketException | UnknownHostException e) {

			e.printStackTrace();
		}

		enviarMensaje("Conexion exitosa");

	}

	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
		try {
			conexion.send(dp);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		do {
			// cuando moris deberias poner el fin en true
			byte[] data = new byte[1024]; // 1024 es el maximo que acepta el datagrama (en el mensaje)
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				conexion.receive(dp);
			} catch (IOException e) {

				e.printStackTrace();
			}

			procesarMensaje(dp);
		} while (!ConfiguracionesCliente.fin);

	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim(); // El trim le saca todos los espacios. Convierto de byte a
														// String. Como no se sabe cuantos caracteres va a tener el
														// mensaje
		// Siempre toma un maximo de 1024, por lo tanto si hay 2 letras lo demï¿½s
		// serian
		// espacios, con el trim estos espacios se eliminan
		// Mensaje que recibe al conectarse

		if (msg.equals("Empieza el online")) {
			ConfiguracionesCliente.empiezaOnlineC = true;
			ConfiguracionesCliente.dispose = true;
		}

		String[] mensajeParametrizado = msg.split("/"); // Mensjae que recibe del sv

		if (mensajeParametrizado[0].equals("Crea")) {
			if (mensajeParametrizado[1].equals("Cronometro")) {
				float tiempoInicial = Float.parseFloat(mensajeParametrizado[2]);
				pj.crearCronometro(tiempoInicial);
			}

			if (mensajeParametrizado[1].equals("Jugadores")) {

			}

			if (mensajeParametrizado[1].equals("Camara")) {

				pj.crearCamara();

			}
			if (mensajeParametrizado[1].equals("Personajes")) {
				//// pj.crearPersonajes();
			}
			if (mensajeParametrizado[1].equals("Mapa")) {
				// pj.crearMapa();
			}
			if (mensajeParametrizado[1].equals("Coaliciones")) {
				// pj.crearCoaliciones();
			}
			/*
			 * if (mensajeParametrizado[1].equals("Camara")) {
			 * 
			 * if (mensajeParametrizado[2].equals("P1")) { pj.crearCamara(1); }
			 * 
			 * if (mensajeParametrizado[2].equals("P2")) { pj.crearCamara(2); } }
			 */
		}

		if (mensajeParametrizado[0].equals("Asigna")) {
			if (mensajeParametrizado[1].equals("Personaje")) {
				if (mensajeParametrizado[2].equals("Jugador")) {
					// pj.asignarPersonajes();
				}
				if (mensajeParametrizado[2].equals("Fixture")) {
					// pj.crearFixtures();
				}
			}
		}

		if (mensajeParametrizado[0].equals("Inicializa")) {
			if (mensajeParametrizado[1].equals("Posicion")) {
				if (mensajeParametrizado[2].equals("Camara")) {
					float valorX = Float.parseFloat(mensajeParametrizado[3]);
					float valorY = Float.parseFloat(mensajeParametrizado[4]);

					pj.inicializarCamaraCliente(valorX, valorY);

				}

			}
		}

		if (mensajeParametrizado[0].equals("Actualizar")) {

			if (mensajeParametrizado[1].equals("Cronometro")) {
				float tiempo = Float.parseFloat(mensajeParametrizado[2]);
				pj.actualizarCronometro(tiempo);
			}

			if (mensajeParametrizado[1].equals("Camara")) {
				if (mensajeParametrizado[2].equals("Zoom")) {
					float zoomCam = Float.parseFloat(mensajeParametrizado[3]);
					pj.cambiarZoomCamara(zoomCam);
				}
				if (mensajeParametrizado[2].equals("PosY")) {
					float posY = Float.parseFloat(mensajeParametrizado[3]);
					pj.actualizarPosCamY(posY);
				}
			}
			if (Utiles.yaCreeJugadores) {
				if (mensajeParametrizado[1].equals("Vida")) {
				
					if (mensajeParametrizado[2].equals("P1")) {
						float vida = Float.parseFloat(mensajeParametrizado[3]);
						pj.setVidaPersonaje(1, vida);
					}
					if (mensajeParametrizado[2].equals("P2")) {
						float vida = Float.parseFloat(mensajeParametrizado[3]);
						pj.setVidaPersonaje(2, vida);
						
					}
				}

				if (mensajeParametrizado[1].equals("HUD")) {
					int idJugador = 0;
					if (mensajeParametrizado[2].equals("P1")) {
						idJugador = 1;
						float puntosJugador = Float.parseFloat(mensajeParametrizado[3]);
						int vidaJugador = (int) Float.parseFloat(mensajeParametrizado[4]);
						int energiaJugador = (int) Float.parseFloat(mensajeParametrizado[5]);
						pj.actualizarPuntosVidaHUD(idJugador, puntosJugador, vidaJugador, energiaJugador);
					}
					if (mensajeParametrizado[2].equals("P2")) {
						idJugador = 2;
						float puntosJugador = Float.parseFloat(mensajeParametrizado[3]);
						int vidaJugador = (int) Float.parseFloat(mensajeParametrizado[4]);
						int energiaJugador = (int) Float.parseFloat(mensajeParametrizado[5]);
						pj.actualizarPuntosVidaHUD(idJugador, puntosJugador, vidaJugador, energiaJugador);
					}

					/*
					 * if(mensajeParametrizado[2].equals("P1")) { float puntosJugador =
					 * Float.parseFloat(mensajeParametrizado[3]); int vidaJugador = (int)
					 * Float.parseFloat(mensajeParametrizado[4]); }
					 * if(mensajeParametrizado[2].equals("P2")) { float puntosJugador =
					 * Float.parseFloat(mensajeParametrizado[3]); int vidaJugador = (int)
					 * Float.parseFloat(mensajeParametrizado[4]); }
					 */

				}

				/*
				 * if (mensajeParametrizado[1].equals("Camara")) { float camY; float camX;
				 * 
				 * if (mensajeParametrizado[2].equals("P1")) { camY =
				 * Float.parseFloat(mensajeParametrizado[3]); camX =
				 * Float.parseFloat(mensajeParametrizado[4]); pj.actualizarCamara(1, camY,
				 * camX); }
				 * 
				 * if (mensajeParametrizado[2].equals("P2")) { camY =
				 * Float.parseFloat(mensajeParametrizado[3]); camX =
				 * Float.parseFloat(mensajeParametrizado[4]); pj.actualizarCamara(2, camY,
				 * camX); } }ç
				 */

				if (mensajeParametrizado[1].equals("Posicion")) {
					if (mensajeParametrizado[2].equals("P1")) {
						if (mensajeParametrizado[3].equals("Derecha")) {
							float velocidadX = Float.parseFloat(mensajeParametrizado[4]);
							pj.setSetRight1(true, 1, velocidadX);

						}
						if (mensajeParametrizado[3].equals("Izquierda")) {
							float velocidadX = Float.parseFloat(mensajeParametrizado[4]);
							pj.setSetLeft1(true, 1, -velocidadX);
						}
						if (mensajeParametrizado[3].equals("Salto")) {
							float velocidadY = Float.parseFloat(mensajeParametrizado[4]);
							float energiaSalto = Float.parseFloat(mensajeParametrizado[5]);
							pj.setSpace1(true, 1, velocidadY, energiaSalto);
						}

					} else if (mensajeParametrizado[2].equals("P2")) {

						if (mensajeParametrizado[3].equals("Derecha")) {

							float velocidadX = Float.parseFloat(mensajeParametrizado[4]);
							pj.setSetRight2(true, 2, velocidadX);

						}
						if (mensajeParametrizado[3].equals("Izquierda")) {
							float velocidadX = Float.parseFloat(mensajeParametrizado[4]);
							pj.setSetLeft2(true, 2, -velocidadX);
						}
						if (mensajeParametrizado[3].equals("Salto")) {
							float velocidadY = Float.parseFloat(mensajeParametrizado[4]);
							float energiaSalto = Float.parseFloat(mensajeParametrizado[5]);
							pj.setSpace2(true, 2, velocidadY, energiaSalto);

						}

					}
				}

				if (mensajeParametrizado[1].equals("Puntuacion")) {
					if (mensajeParametrizado[2].equals("Jugador1")) {
						float puntuacion = Float.parseFloat(mensajeParametrizado[3]);
						pj.sumarPuntos(1, puntuacion);
					}
					if (mensajeParametrizado[2].equals("Jugador2")) {
						float puntuacion = Float.parseFloat(mensajeParametrizado[3]);
						pj.sumarPuntos(2, puntuacion);
					}

				}

				if (mensajeParametrizado[1].equals("Velocidad")) {
					if (mensajeParametrizado[2].equals("P1")) {

						float velocidadModif = Float.parseFloat(mensajeParametrizado[3]);
						float energia = Float.parseFloat(mensajeParametrizado[4]);
						pj.setShift1(true, 1, velocidadModif, energia);

					}
					if (mensajeParametrizado[2].equals("P2")) {
						float velocidadModif = Float.parseFloat(mensajeParametrizado[3]);
						float energia = Float.parseFloat(mensajeParametrizado[4]);
						pj.setShift1(true, 2, velocidadModif, energia);
					}

					if (mensajeParametrizado[2].equals("Y")) {

						if (mensajeParametrizado[3].equals("P1")) {

							float velocidadYModif = Float.parseFloat(mensajeParametrizado[4]);
							pj.setVelocidadY(1, velocidadYModif);

						}

						if (mensajeParametrizado[3].equals("P2")) {
							float velocidadYModif = Float.parseFloat(mensajeParametrizado[4]);
							pj.setVelocidadY(2, velocidadYModif);

						}

					}
				}
				if (mensajeParametrizado[1].equals("EnergiaSalto")) {
					if (mensajeParametrizado[2].equals("P1")) {
						float energiaSalto = Float.parseFloat(mensajeParametrizado[3]);
						pj.aumentarEnergiaPersonaje(1, energiaSalto);

					}

					if (mensajeParametrizado[2].equals("P2")) {
						float energiaSalto = Float.parseFloat(mensajeParametrizado[3]);
						pj.aumentarEnergiaPersonaje(2, energiaSalto);

					}
				}
			}
		}

			if (mensajeParametrizado[0].equals("Fin")) {
				if (mensajeParametrizado[1].equals("Juego")) {
					if (mensajeParametrizado[2].equals("AmbosPerdieron")) {
						String opcion = "ambosPerdieron";

						pj.finJuego(opcion);
					}
					if (mensajeParametrizado[2].equals("AmbosGanaron")) {
						String opcion = "ambosGanaron";
						pj.finJuego(opcion);
					}
				}
			}
			if (mensajeParametrizado[0].equals("Salir")) {
				if (mensajeParametrizado[1].equals("Juego")) {
					Gdx.app.exit();

				}
			}
			if (mensajeParametrizado[0].equals("Destruir")) {
				if (mensajeParametrizado[1].equals("Moneda")) {
					pj.destruirMoneda();
				}
			}
	
		/*
		 * 
		 * if (mensajeParametrizado[0].equals("Actualizar")) { if
		 * (mensajeParametrizado[1].equals("P1")) { if
		 * (mensajeParametrizado[2].equals("Y")) {
		 * 
		 * if (mensajeParametrizado[3].equals("arriba")) { float posY =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * //pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), // pj.getJugador1(), "arriba", posY);
		 * 
		 * }
		 * 
		 * else { float posY = Float.parseFloat(mensajeParametrizado[4]);
		 * //pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), // pj.getJugador1(), "abajo", posY); } } if
		 * (mensajeParametrizado[2].equals("X")) {
		 * 
		 * if (mensajeParametrizado[3].equals("derecha")) { float posX =
		 * Float.parseFloat(mensajeParametrizado[4]); //
		 * pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), // pj.getJugador1(), "derecha", posX); }
		 * 
		 * else { float posX = Float.parseFloat(mensajeParametrizado[4]);
		 * //pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), //pj.getJugador1(), "izquierda", posX); }
		 * 
		 * } }
		 * 
		 * else { if (mensajeParametrizado[2].equals("Y")) {
		 * 
		 * if (mensajeParametrizado[3].equals("arriba")) { float posY =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * //pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), // pj.getJugador2(), "arriba", posY); }
		 * 
		 * else { float posY = Float.parseFloat(mensajeParametrizado[4]);
		 * //pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), //pj.getJugador2(), "abajo", posY); } } if
		 * (mensajeParametrizado[2].equals("X")) {
		 * 
		 * if (mensajeParametrizado[3].equals("derecha")) { float posX =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), pj.getJugador2(), "derecha", posX); }
		 * 
		 * else { float posX = Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), pj.getJugador2(), "izquierda", posX); }
		 * 
		 * } } }
		 * 
		 * if (mensajeParametrizado[0].equals("DejoDeMoverse")) { if
		 * (mensajeParametrizado[1].equals("P1")) { if
		 * (mensajeParametrizado[2].equals("X")) {
		 * 
		 * if (mensajeParametrizado[3].equals("izquierda")) { float posX =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), pj.getJugador1(), "izquierda", posX); }
		 * 
		 * else if (mensajeParametrizado[3].equals("derecha")) { float posX =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), pj.getJugador1(), "derecha", posX); } }
		 * 
		 * else if (mensajeParametrizado[2].equals("Y")) {
		 * 
		 * if (mensajeParametrizado[3].equals("arriba")) { float posY =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), pj.getJugador1(), "arriba", posY); }
		 * 
		 * else if (mensajeParametrizado[3].equals("abajo")) { float posY =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador1().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion(), pj.getJugador1(), "abajo", posY); } }
		 * 
		 * }
		 * 
		 * else { if (mensajeParametrizado[2].equals("X")) {
		 * 
		 * if (mensajeParametrizado[3].equals("izquierda")) { float posX =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), pj.getJugador2(), "izquierda", posX); }
		 * 
		 * else if (mensajeParametrizado[3].equals("derecha")) { float posX =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), pj.getJugador2(), "derecha", posX); } }
		 * 
		 * else if (mensajeParametrizado[2].equals("Y")) {
		 * 
		 * if (mensajeParametrizado[3].equals("arriba")) { float posY =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), pj.getJugador2(), "arriba", posY); }
		 * 
		 * else if (mensajeParametrizado[3].equals("abajo")) { float posY =
		 * Float.parseFloat(mensajeParametrizado[4]);
		 * pj.getJugador2().getSerpienteJugador().actualizarPosicionesOnline(pj.
		 * getPosicion2(), pj.getJugador2(), "abajo", posY); } } }
		 * 
		 * }
		 * 
		 * if (mensajeParametrizado[0].equals("Actualizar")) { if
		 * (mensajeParametrizado[1].equals("Puntaje")) { if
		 * (mensajeParametrizado[2].equals("P1")) {
		 * 
		 * float puntos = Float.parseFloat(mensajeParametrizado[3]);
		 * pj.getJugador1().sumarPuntuacion(puntos);
		 * enviarMensaje("Deje de coalicionar");
		 * 
		 * }
		 * 
		 * else { float puntos = Float.parseFloat(mensajeParametrizado[3]);
		 * pj.getJugador2().sumarPuntuacion(puntos);
		 * pj.update(Gdx.graphics.getDeltaTime()); enviarMensaje("Deje de coalicionar");
		 * 
		 * } }
		 * 
		 * } if (mensajeParametrizado[0].equals("Elservidor")) { if
		 * (mensajeParametrizado[1].equals("creo")) { if
		 * (mensajeParametrizado[2].equals("lacomida")) { float posXComida =
		 * Float.parseFloat(mensajeParametrizado[3]); float posYComida =
		 * Float.parseFloat(mensajeParametrizado[4]); float posXComidaVector2 =
		 * Float.parseFloat(mensajeParametrizado[5]); float posYComidaVector2 =
		 * Float.parseFloat(mensajeParametrizado[6]); pj.crearComidaOnline(posXComida,
		 * posYComida, posXComidaVector2, posYComidaVector2); } } }
		 * 
		 * if (mensajeParametrizado[0].equals("dibujeComida")) { float posXComida =
		 * Float.parseFloat(mensajeParametrizado[1]); float posYComida =
		 * Float.parseFloat(mensajeParametrizado[2]); pj.dibujarComidaOnline(posXComida,
		 * posYComida); }
		 * 
		 * if (mensajeParametrizado[0].equals("CoalicionTrue")) {
		 * Configuraciones.realizarCoalicion = true; }
		 * 
		 * if (mensajeParametrizado[0].equals("spawneeComida")) { float posComidaX =
		 * Float.parseFloat(mensajeParametrizado[1]); float posComidaY =
		 * Float.parseFloat(mensajeParametrizado[2]);
		 * pj.spawnearComidaOnline(posComidaX, posComidaY); }
		 * 
		 * if (mensajeParametrizado[0].equals("RealizarCoalicion")) { if
		 * (mensajeParametrizado[1].equals("P1")) {
		 * 
		 * pj.coalicionesOnline(1); } else {
		 * 
		 * pj.coalicionesOnline(2);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * if (mensajeParametrizado[0].equals("CreaElCronometro")) { if
		 * (mensajeParametrizado[1].equals("unicaVez")) { pj.crearCronometroOnline(); }
		 * }
		 * 
		 * if (mensajeParametrizado[0].equals("GanoPersonaje")) { if
		 * (mensajeParametrizado[2].equals("puntos")) { String nombreJugador =
		 * String.valueOf(mensajeParametrizado[1]); float puntosRealizados =
		 * Float.parseFloat(mensajeParametrizado[3]); pj.setGanadorOnline(nombreJugador,
		 * puntosRealizados); } }
		 * 
		 * if (mensajeParametrizado[0].equals("ActualizarCronometro")) {
		 * 
		 * float tiempoJuego = Float.parseFloat(mensajeParametrizado[1]);
		 * pj.actualizarTiempoJuegoOnline(tiempoJuego);
		 * 
		 * }
		 * 
		 * if (mensajeParametrizado[0].equals("Reinicia el juego")) {
		 * 
		 * Gdx.app.postRunnable(new Runnable() {
		 * 
		 * @Override public void run() { Configuraciones.empiezaOnlineC = false;
		 * Configuraciones.reinicioJuego = true;
		 * Configuraciones.puntuacionJugador1Creada = false;
		 * Configuraciones.puntuacionJugador2Creada = false;
		 * Configuraciones.tiempoRestanteCreado = false;
		 * Configuraciones.cronometroCreado = false; Configuraciones.spawneoComida =
		 * false; Configuraciones.dispose = true; Configuraciones.finalizarJuego=false;
		 * Configuraciones.finalizarHiloTiempo=true; Render.batch.end();
		 * Configuraciones.finalizarJuego=false; Configuraciones.fin=true;
		 * Render.app.setScreen(new PantallaJuego()); } });
		 * 
		 * 
		 * }
		 * 
		 * if(mensajeParametrizado[0].equals("Serpiente")) {
		 * if(mensajeParametrizado[1].equals("ChocaConSiMisma")) {
		 * if(mensajeParametrizado[2].equals("P1")) { enviarMensaje("RestaPuntos P1"); }
		 * else { enviarMensaje("RestaPuntos P2"); } } }
		 * 
		 * 
		 * 
		 * 
		 * if(mensajeParametrizado[0].equals("Restar")) {
		 * if(mensajeParametrizado[1].equals("Puntaje")) { Float puntos =
		 * Float.parseFloat(mensajeParametrizado[3]);
		 * if(mensajeParametrizado[2].equals("P1")) {
		 * pj.getJugador1().setPuntosOnline(puntos); } else {
		 * pj.getJugador2().setPuntosOnline(puntos); } } }
		 * 
		 * 
		 * 
		 * if(mensajeParametrizado[0].equals("AchicaSerpiente")) { float posicion =
		 * Float.parseFloat(mensajeParametrizado[2]);
		 * if(mensajeParametrizado[1].equals("P1")) {
		 * pj.getJugador1().getSerpienteJugador().achicarSerpiente(posicion, 1); } else
		 * { pj.getJugador2().getSerpienteJugador().achicarSerpiente(posicion, 2); } }
		 * 
		 */
	}

}
