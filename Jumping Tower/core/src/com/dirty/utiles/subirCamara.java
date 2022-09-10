package com.dirty.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dirty.camara.Camara;
import com.dirty.mapas.Mapa;

public class subirCamara implements subirCamaraEventListener {

	@Override
	public void subirCamara(Camara camara, Mapa mapa) {
		float valorCamY;
		valorCamY=camara.getCamaraJuego().position.y+300;
		
			valorCamY = camara.getCamaraJuego().position.y + 300;
			if (valorCamY < mapa.getAltoMapa()) {
			if (valorCamY < 7500) {
					camara.getCamaraJuego().position.y += 300 ;
				} else {

			}
			} else {

			}
		
	}

}
