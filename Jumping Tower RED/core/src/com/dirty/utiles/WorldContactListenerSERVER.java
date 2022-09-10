package com.dirty.utiles;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import com.dirty.jugadores.JugadorBASESERVER;
import com.dirty.personajes.PersonajeSERVER;

public class WorldContactListenerSERVER implements ContactListener {

	PersonajeSERVER personaje;
	Integer itemUsado = 0;
	JugadorBASESERVER []jugador= new JugadorBASESERVER[2];
	private int indiceJugador;

	public WorldContactListenerSERVER(PersonajeSERVER personaje) {
		this.personaje = personaje;
		
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if (fixA.getUserData() == "cuerpo" || fixB.getUserData() == "cuerpo") {
			Fixture cuerpo = fixA.getUserData() == "cuerpo" ? fixA : fixB;
			Fixture objeto = cuerpo == fixA ? fixB : fixA;
					
		
				if(objeto.getUserData() != null && InteractiveTileObjectSERVER.class.isAssignableFrom(objeto.getUserData().getClass())) {
					
					((InteractiveTileObjectSERVER) objeto.getUserData()).choque(jugador[0]);
				
				}
			
				
			
			

		}
		
		if (fixA.getUserData() == "cuerpo2" || fixB.getUserData() == "cuerpo2") {
			Fixture cuerpo2 = fixA.getUserData() == "cuerpo2" ? fixA : fixB;
			Fixture objeto = cuerpo2 == fixA ? fixB : fixA;
			
		if(objeto.getUserData() != null && InteractiveTileObjectSERVER.class.isAssignableFrom(objeto.getUserData().getClass())) {
			((InteractiveTileObjectSERVER) objeto.getUserData()).choque(jugador[1]);
		}
		}
		
		
		

//		else if (fixA.getUserData() == "cuerpo" || fixB.getUserData() == "cuerpo") {
//			Fixture cuerpo = fixA.getUserData() == "cuerpo" ? fixA : fixB;
//			Fixture objeto = cuerpo == fixA ? fixB : fixA;
//
//			if (objeto.getUserData() != null && Item.class.isAssignableFrom(objeto.getUserData().getClass())) {
//				((Item) objeto.getUserData()).use();
//			}
//		}
//
//		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
//		if (fixA.getUserData() == "enemigo" || fixB.getUserData() == "enemigo") {
//			Fixture enemigo2 = fixA.getUserData() == "enemigo" ? fixA : fixB;
//			Fixture object = enemigo2 == fixA ? fixB : fixA;
//
//			if (fixA.getFilterData().categoryBits == 1) {
//			
//				enemigo.restarVida(10);
//
//				if (enemigo.getVida() <= 0) {
//					enemigo.matarEnemigo();
//
//				}
//			}
//
//			else if (fixB.getFilterData().categoryBits == Configuraciones.ENEMIGO_BIT) {
//	
//				enemigo.restarVida(10);
//				if (enemigo.getVida() <= 0) {
//					enemigo.matarEnemigo();
//				}
//
//			}
//		}
//
//		itemCurativo(fixA, fixB);
//		item(fixA, fixB);
//
////		switch (cDef) {
////		case Configuraciones.ITEMS_BIT | Configuraciones.PERSONAJE_BIT:
////			System.out.println("Entra al case 1");
////			if (fixA.getFilterData().categoryBits == Configuraciones.ITEMS_BIT) {
////				System.out.println("choca con personaje");
////				((Item) fixA.getUserData()).use();
////			} else{
////				System.out.println("choca con personaje");
////				((Item) fixB.getUserData()).use();
////			}
////			break;
////		case Configuraciones.DEFAULT_BIT | Configuraciones.DEFAULT_BIT:
////			System.out.println("Case default");
////			System.err.println(cDef);
////			break;
////		}
	}
	
	public void setJugador(JugadorBASESERVER jugador, int indice) {
		if (indice==0) {
			this.jugador[0]=jugador;
			this.indiceJugador=indice;
		}else
		{
			this.jugador[1]=jugador;
			this.indiceJugador=indice;
		}
	
		
	}
/*
	private void itemCurativo(Fixture fixA, Fixture fixB) {

		if (fixA.getUserData() == "item curativo" || fixB.getUserData() == "item curativo") {
			Fixture cuerpo = fixA.getUserData() == "item curativo" ? fixA : fixB;
			Fixture objeto = cuerpo == fixA ? fixB : fixA;

			if (fixA.getUserData() == "cuerpo" || fixB.getUserData() == "cuerpo") {
				if (itemUsado == 0) {
					personaje.setVida(itemCurativo.getRecuperaVida());

					itemUsado++;

				} else {
					System.out.println("Lo está chocando un enemigo, boludo");

				}
			}
		}
	}

	private void item(Fixture fixA, Fixture fixB) {
		if (fixA.getUserData() == "item usable" || fixB.getUserData() == "item usable") {
			Fixture cuerpo = fixA.getUserData() == "item usable" ? fixA : fixB;
			Fixture objeto = cuerpo == fixA ? fixB : fixA;
			System.out.println("Tocaste un " + itemUsable.getNombre());
			if (itemUsable.getNombre().equals("Tarjeta Seguridad")) {
				personaje.setTarjetaMap(true);
			}

		}
	}
	*/ 

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub



	}
}
	