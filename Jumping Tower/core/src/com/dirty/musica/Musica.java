package com.dirty.musica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Musica implements Music {
	private String direccion;
	public Musica(String direccion) {
		this.direccion=direccion;
		
	}

	public void crearMusica(Music musica) {
		musica=Gdx.audio.newMusic(Gdx.files.internal(direccion));
		musica.setLooping(true);
		musica.setVolume(0.1f);
		musica.play();
	
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLooping(boolean isLooping) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLooping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVolume(float volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPan(float pan, float volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(float position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOnCompletionListener(OnCompletionListener listener) {
		// TODO Auto-generated method stub
		
	}

}
