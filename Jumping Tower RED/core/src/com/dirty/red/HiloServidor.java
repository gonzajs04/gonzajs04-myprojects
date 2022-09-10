package com.dirty.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.badlogic.gdx.Gdx;
import com.dirty.configuraciones.ConfiguracionesSERVIDOR;
import com.dirty.pantallas.PantallaJuegoSERVER;
import com.dirty.utiles.UtilesSERVER;

public class HiloServidor extends Thread {
	// Va a controlar la conexion y actividad de los clientes en el server
	private DatagramSocket conexion;
	private boolean fin = false;
	private Direccion[] clientes = new Direccion[2];
	private int cantClientes = 0;
	private PantallaJuegoSERVER pj;
	private Integer primerCliente;

	public HiloServidor(PantallaJuegoSERVER pj) {
		this.pj = pj;

		try {
			conexion = new DatagramSocket(9999);
		} catch (SocketException e) {

			e.printStackTrace();
		}

	}

	private void enviarMensaje(String msg, InetAddress ip, int puerto) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ip, puerto);
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
		} while (!fin);

	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim(); // El trim le saca todos los espacios. Convierto de byte a
														// String.
														// Como no se sabe cuantos caracteres va a tener el mensaje
														// Siempre toma un maximo de 1024, por lo tanto si hay 2 letras
														// // lo dem�s serian espacios, con el trim estos espacios se
														// eliminan

		int nroCliente = -1;
		if (cantClientes > 1) {
			for (int i = 0; i < clientes.length; i++) {
				if (dp.getPort() == clientes[i].getPuerto() && dp.getAddress().equals(clientes[i].getIp())) {
					nroCliente = i;
				}
			}

		}

		/*
		 * if (msg.equals("Se ha creado el personaje con indice 0")) {
		 * enviarMensaje("�Se te ha asignado el primer personaje!",
		 * clientes[0].getIp(), clientes[0].getPuerto()); }
		 */
		if (cantClientes < 2) {
			if (msg.equals("Conexion exitosa")) {
				if (cantClientes < 2) {
					clientes[cantClientes] = new Direccion(dp.getAddress(), dp.getPort()); // Creo un nuevo cliente bajo
																							// la
																							// ip y el puerto que
																							// ingresa y
																							// se suma al momento de
																							// agregar
																							// uno nuevo
					enviarMensaje("OK", clientes[cantClientes].getIp(), clientes[cantClientes].getPuerto());
					// Al momento que le mandamos un OK al cliente, de esta forma, el cliente sabe
					// que encontr� un servidor y se queda en este.
				}
				cantClientes++;

				if (cantClientes == 1) {
					for (int i = 0; i < clientes.length; i++) {
						if (i == 0) {
							primerCliente = i;

						}
					}

					enviarMensaje("primero", clientes[primerCliente].getIp(), clientes[primerCliente].getPuerto());

				}

				else if (cantClientes == 2) {

					ConfiguracionesSERVIDOR.empiezaOnlineC = true;

					for (int i = 0; i < clientes.length; i++) {
						enviarMensaje("Empieza el online", clientes[i].getIp(), clientes[i].getPuerto()); // Apenas
						ConfiguracionesSERVIDOR.empiezaOnlineC = true; // sucede
																		// esto, va
																		// // en
																		// procesarMensaje
					}

				}

				else if (cantClientes == 2 && (!ConfiguracionesSERVIDOR.disposeBool)) {
					ConfiguracionesSERVIDOR.disposeBool = true;
					ConfiguracionesSERVIDOR.dispose = true;
				}
			}
		}

		if (msg.equals("Cliente creo cronometro")) { // este mensaje lo envia el cliente cuando crea el cronometro
			// ac� no va nada porque el hilo actualiza por si mismo del sv, y enviamos los
			// valores desde HiloTIEMPOSERVER.
		}

		if (msg.equals("Aprete derecha")) {

			if (nroCliente == 0) {

				pj.setRight1(true, 1);
			}
			if (nroCliente == 1) {
				pj.setRight2(true, 2);
			}
		}
		if (msg.equals("Aprete izquierda")) {

			if (nroCliente == 0) {
				pj.setLeft1(true, 1);
			}
			if (nroCliente == 1) {
				pj.setLeft2(true, 2);
			}

		}
		if (msg.equals("Aprete space")) {

			if (nroCliente == 0) {
				pj.setSpace1(true, 1);

			}
			if (nroCliente == 1) {
				pj.setSpace2(true, 2);
			}
			if (UtilesSERVER.yaSalte == false) {
				pj.subirCamaraAutomaticamente();
				UtilesSERVER.yaSalte = true;
			}

		}

		if (msg.equals("Cai debajo cam")) {

			if (nroCliente == 0) {
				pj.restarVida(0.06f, true, 2);
			}

			if (nroCliente == 1) {

				pj.restarVida(0.06f, true, 1);
			}
		}

		if (msg.equals("Aprete shift")) {

			if (nroCliente == 0) {

				pj.setShift1(true, 1);
			}
			if (nroCliente == 1) {
				pj.setShift2(true, 2);

			}

		}

		if (msg.equals("Fin juego P1/P2")) {
			pj.finJuego();

		}
		/*
		 * if(msg.equals("Inicializa posicion camara P1")) {
		 * pj.inicializarCamaraPersonaje(1); }
		 * 
		 * if(msg.equals("Inicializa posicion camara P2")) {
		 * pj.inicializarCamaraPersonaje(2); }
		 */

		if (msg.equals("Actualice finjuego")) {

			pj.setEstadoVictoria();

		}

		if (msg.equals("Choco Batman")) {
			pj.restarVida(15f, true, 1);
		}
		if (msg.equals("Choco Goku")) {
			pj.restarVida(15f, true, 2);
		}

		if (msg.equals("ChocoMonedaGoku")) {

			pj.aumentarPuntos(2);

		}

		if (msg.equals("ChocoMonedaBatman")) {

			pj.aumentarPuntos(1);

		}
		
		if(msg.equals("GokuChocoVida")) {
			pj.aumentarVida(2,50f);
			
			
		}
		if(msg.equals("BatmanChocoVida")) {
			
			pj.aumentarVida(1,50f);
			
		}
		if (msg.equals("Voy a cerrar juego")) {
			if (nroCliente == 0) {
				Gdx.app.exit();
			}
			if (nroCliente == 1) {

				Gdx.app.exit();

			}
		}

	}

	/*
	 * else { if (nroCliente != -1) {
	 * 
	 * if (msg.equals("Aprete arriba")) {
	 * 
	 * if (nroCliente == 0) { pj.setUp1(true); } else { pj.setUp2(true);
	 * 
	 * } } else if (msg.equals("Aprete abajo")) { if (nroCliente == 0) {
	 * pj.setDown1(true); } else { pj.setDown2(true);
	 * 
	 * } } else if (msg.equals("Aprete izquierda")) { if (nroCliente == 0) {
	 * pj.setLeft1(true);
	 * 
	 * } else { pj.setLeft2(true);
	 * 
	 * } } else if (msg.equals("Aprete derecha")) { if (nroCliente == 0) {
	 * pj.setRight1(true);
	 * 
	 * } else { pj.setRight2(true);
	 * 
	 * } } else if (msg.equals("Deje de apretar arriba")) { if (nroCliente == 0) {
	 * ConfiguracionesSERVIDOR.estaApretando = false; pj.setUp1(false);
	 * 
	 * } else { ConfiguracionesSERVIDOR.estaApretando = false; pj.setUp2(false);
	 * 
	 * } } else if (msg.equals("Deje de apretar abajo")) { if (nroCliente == 0) {
	 * ConfiguracionesSERVIDOR.estaApretando = false; pj.setDown1(false);
	 * 
	 * } else { ConfiguracionesSERVIDOR.estaApretando = false; pj.setDown2(false);
	 * 
	 * } } else if (msg.equals("Deje de apretar derecha")) { if (nroCliente == 0) {
	 * ConfiguracionesSERVIDOR.estaApretando = false; pj.setRight1(false);
	 * 
	 * } else { ConfiguracionesSERVIDOR.estaApretando = false; pj.setRight2(false);
	 * 
	 * } } else if (msg.equals("Deje de apretar izquierda")) { if (nroCliente == 0)
	 * { ConfiguracionesSERVIDOR.estaApretando = false; pj.setLeft1(false);
	 * 
	 * } else { ConfiguracionesSERVIDOR.estaApretando = false; pj.setLeft2(false);
	 * 
	 * } } }
	 * 
	 * }
	 * 
	 * if (msg.equals("Estoy coalicionando")) {
	 * ConfiguracionesSERVIDOR.hs.enviarMensajeParaTodos("CoalicionTrue"); }
	 * 
	 * if (msg.equals("Suma puntos P1")) {
	 * 
	 * pj.setSumarPuntos1(true); pj.actualizarPuntuacionOnline();
	 * 
	 * }
	 * 
	 * else if (msg.equals("Suma puntos P2")) { pj.setSumarPuntos2(true);
	 * pj.actualizarPuntuacionOnline(); }
	 * 
	 * if (msg.equals("creoLaComida")) { pj.dibujarComidaOnline(); }
	 * 
	 * if (msg.equals("dibujeLaComida")) { pj.spawnearComidaOnline(); }
	 * 
	 * if (msg.equals("Deje de coalicionar")) {
	 * ConfiguracionesSERVIDOR.coalicionando = false; }
	 * 
	 * if (msg.equals("Aprete la R")) {
	 * 
	 * Gdx.app.postRunnable(new Runnable() {
	 * 
	 * @Override public void run() { ConfiguracionesSERVIDOR.empiezaOnlineC = false;
	 * ConfiguracionesSERVIDOR.reinicioJuego = true;
	 * ConfiguracionesSERVIDOR.puntuacionJugador1noCreada = false;
	 * ConfiguracionesSERVIDOR.puntuacionJugador2noCreada = false;
	 * ConfiguracionesSERVIDOR.crearCronometroTexto = false;
	 * ConfiguracionesSERVIDOR.creoCabeza = false;
	 * ConfiguracionesSERVIDOR.puntuacion3noCreada = false;
	 * ConfiguracionesSERVIDOR.creoComida = false;
	 * ConfiguracionesSERVIDOR.creoCronometro = false;
	 * ConfiguracionesSERVIDOR.dispose = false; ConfiguracionesSERVIDOR.disposeBool
	 * = true; ConfiguracionesSERVIDOR.estaApretando = false;
	 * ConfiguracionesSERVIDOR.chocandoASiMismo=false;
	 * ConfiguracionesSERVIDOR.chocandoASiMismo2=false;
	 * ConfiguracionesSERVIDOR.estaApretando=false;
	 * ConfiguracionesSERVIDOR.serpiente1Choca=false;
	 * ConfiguracionesSERVIDOR.serpiente2Choca=false;
	 * ConfiguracionesSERVIDOR.finalizarHiloTiempo=true; cantClientes=0;
	 * ConfiguracionesSERVIDOR.hs.enviarMensajeParaTodos("Reinicia el juego");
	 * Render.batch.end(); Render.app.setScreen(new PantallaJuegoServer()); } });
	 * 
	 * }
	 * 
	 * if(msg.equals("RestaPuntos P1")) { pj.getJugador1().restarPuntuacion(10);
	 * ConfiguracionesSERVIDOR.chocandoASiMismo = false; }
	 * if(msg.equals("RestaPuntos P2")) { pj.getJugador2().restarPuntuacion(10);
	 * ConfiguracionesSERVIDOR.chocandoASiMismo2 = false; }
	 * 
	 * }
	 */

	public void enviarMensajeParaTodos(String msg) {
		for (int i = 0; i < clientes.length; i++) {
			enviarMensaje(msg, clientes[i].getIp(), clientes[i].getPuerto());
		} // se manda un mensaje general a todos los clientes conectados
	}
}
