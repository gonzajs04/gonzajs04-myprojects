package com.dirty.configuraciones;

import com.dirty.red.HiloServidor;

public enum ConfiguracionesSERVER {
	
	PPM("PPM",0.5f);
	public static HiloServidor hs; //creo el hiloservidor de forma estática
	private float valor;
	public static boolean ambosPerdieron=false;
	private String nombre;
	ConfiguracionesSERVER(String nombre,float valor){
		this.nombre=nombre;
		this.valor=valor;
	}
	public float getValor() {
		return valor;
	}
	public String getNombre() {
		return nombre;
	}

}
