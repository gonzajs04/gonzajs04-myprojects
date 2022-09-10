package com.dirty.entradas;

import com.badlogic.gdx.Input.Keys;
import com.dirty.utiles.Utiles;

import configuraciones.Configuraciones;
import configuraciones.ConfiguracionesCliente;

import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {

	private boolean abajo = false, arriba = false, enter = false, izquierda = false, derecha = false, touchPad = false,
			saltar = true, shift = false;
	private int mouseX = 0, mouseY = 0;
	private boolean click = false;

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.S) {// flecha para abajo
			abajo = true;
			ConfiguracionesCliente.hc.enviarMensaje("Aprete abajo");
		} else if (keycode == Keys.W) {// flecha para arriba
			arriba = true;
			ConfiguracionesCliente.hc.enviarMensaje("Aprete arriba");
		}

		if (keycode == Keys.ENTER) {// tecla enter
			enter = true;
		}

		if (keycode == Keys.SHIFT_LEFT) {// tecla shift
			shift = true;

			ConfiguracionesCliente.hc.enviarMensaje("Aprete shift");
		}

		if (keycode == Keys.A) {
			izquierda = true;
			ConfiguracionesCliente.hc.enviarMensaje("Aprete izquierda");
		} else if (keycode == Keys.D) {
			derecha = true;
			ConfiguracionesCliente.hc.enviarMensaje("Aprete derecha");
		}

		if (keycode == Keys.SPACE) {
			saltar = true;
			ConfiguracionesCliente.hc.enviarMensaje("Aprete space");

		}
		if(keycode== Keys.ESCAPE) {
			ConfiguracionesCliente.hc.enviarMensaje("Voy a cerrar juego");
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.S) {
			abajo = false;
			//ConfiguracionesCliente.hc.enviarMensaje("Deje de apretar abajo");
		}

		if (keycode == Keys.W) {
			arriba = false;
			//ConfiguracionesCliente.hc.enviarMensaje("Deje de apretar arriba");
		}

		if (keycode == Keys.ENTER) {
			enter = false;
		}

		if (keycode == Keys.A) {
			izquierda = false;
			//ConfiguracionesCliente.hc.enviarMensaje("Deje de apretar izquierda");
		}
		if (keycode == Keys.D) {
			derecha = false;
			//ConfiguracionesCliente.hc.enviarMensaje("Deje de apretar derecha");
		}
		if (keycode == Keys.SPACE) {
			saltar = true;
			//ConfiguracionesCliente.hc.enviarMensaje("Deje de apretar space");
			
		}
		
			
		return false;
		}

	public boolean keyLeft(int keycode) {
		if (keycode == Keys.LEFT) {
			izquierda = true;
		}

		if (keycode == Keys.RIGHT) {
			derecha = true;
		}

		return false;

	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		click = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Utiles.vpHeight - screenY; // Para que x 0 esté abajo y no arriba
		return false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public boolean isArriba() {
		return arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isEnter() {
		return enter;
	}

	public boolean isClick() {
		return click;
	}

	public boolean isTouchPad() {
		return touchPad;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
