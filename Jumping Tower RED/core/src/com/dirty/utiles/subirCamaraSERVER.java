package com.dirty.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dirty.camara.CamaraSERVER;
import com.dirty.mapas.MapaSERVER;

public class subirCamaraSERVER implements subirCamaraEventListener {

	@Override
	public void subirCamara(CamaraSERVER camara, MapaSERVER mapa) {
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
