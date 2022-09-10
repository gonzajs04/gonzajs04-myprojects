package com.dirty.entradas;

import com.badlogic.gdx.Input.Keys;
import com.dirty.configuraciones.ConfiguracionesSERVER;
import com.dirty.utiles.UtilesSERVER;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;


public class EntradasSERVER implements InputProcessor {

	private boolean abajo = false, arriba = false, enter = false, izquierda = false, derecha = false, touchPad = false;
	private int mouseX = 0, mouseY = 0;
	private boolean click = false;

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.DOWN || keycode == Keys.S) {// flecha para abajo
			abajo = true;
		} else if (keycode == Keys.UP || keycode == Keys.W) {// flecha para arriba
			arriba = true;
		}

		if (keycode == Keys.ENTER) {// tecla enter
			enter = true;
		}

		if (keycode == Keys.A) {
			izquierda = true;
		} else if (keycode == Keys.D) {
			derecha = true;
		}
		if(keycode==Keys.ESCAPE) {
			Gdx.app.exit();
			ConfiguracionesSERVER.hs.enviarMensajeParaTodos("Salir/Juego");
			
		}
		

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.DOWN || keycode == Keys.S) {
			abajo = false;
		}

		if (keycode == Keys.UP || keycode == Keys.W) {
			arriba = false;
		}

		if (keycode == Keys.ENTER) {
			enter = false;
		}

		if (keycode == Keys.A) {
			izquierda = false;
		}
		if (keycode == Keys.D) {
			derecha = false;
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
		mouseY = UtilesSERVER.vpHeight - screenY; // Para que x 0 esté abajo y no arriba
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
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
