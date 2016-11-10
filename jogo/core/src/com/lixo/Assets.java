package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Assets
{
	public static Texture gramado;
	public static Animation grama;
	public static Texture fundoMenuPrincipal;
	public static Music musica;
	
	
	public static Texture carregarTextura(String textura)
	{
		return new Texture(Gdx.files.internal(textura));
	}
	
	public static void carregarTudo()
	{
		gramado = carregarTextura("gramado.png");
		fundoMenuPrincipal = carregarTextura("fundo_menu.png");
		musica = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		musica.setLooping(true);
		musica.setVolume(0.5f);
		musica.play();
	}
}
