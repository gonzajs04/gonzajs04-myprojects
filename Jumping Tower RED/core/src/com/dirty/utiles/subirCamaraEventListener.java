package com.dirty.utiles;

import java.util.EventListener;

import com.dirty.camara.CamaraSERVER;
import com.dirty.mapas.MapaSERVER;

public interface subirCamaraEventListener extends EventListener {
	
	void subirCamara(CamaraSERVER camara, MapaSERVER mapa);

}
