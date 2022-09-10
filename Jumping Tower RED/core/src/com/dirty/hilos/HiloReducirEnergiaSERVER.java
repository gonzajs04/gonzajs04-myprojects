package com.dirty.hilos;



public class HiloReducirEnergiaSERVER extends Thread {
	private int tics = 0, tiempo = 0, segundos = 0;
	private boolean fin = false;
	private CronometroSERVER cronometro;


	@Override
	public void run() {
		do {
			if (tics++ % 500000000 == 0) {
				tiempo++;
				if (tiempo % 2 == 0) {
					
					

				}
			}
		} while (!fin);
	}

	public int getSegundos() {
		return segundos;
	}
}
