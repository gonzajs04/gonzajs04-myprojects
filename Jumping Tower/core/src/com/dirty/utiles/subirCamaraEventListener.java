package com.dirty.utiles;

import java.util.EventListener;

import com.dirty.camara.Camara;
import com.dirty.mapas.Mapa;

public interface subirCamaraEventListener extends EventListener {
	
	void subirCamara(Camara camara, Mapa mapa);

}
