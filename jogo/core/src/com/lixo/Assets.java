package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets
{
	public static TextureRegion lixeira_vermelha;
	public static TextureRegion lixeira_verde;
	public static TextureRegion lixeira_amarela;
	public static TextureRegion lixeira_azul;
	public static TextureRegion estilingue_tras;
	public static TextureRegion estilingue_frente;
	public static TextureRegion texto_iniciar;
	public static TextureRegion titulo;
	public static TextureRegion som_ligado;
	public static TextureRegion som_desligado;
	public static TextureRegion garrafa_heineken;
	public static Texture gramado;
	public static Texture sprites;
	public static Animation grama;
	public static Texture fundoMenuPrincipal;
	public static Music musica;
	public static boolean sons;
	public static final int TELA_LARGURA = 640;
	public static final int TELA_ALTURA = 480;
	
	public static Texture carregarTextura(String textura)
	{
		return new Texture(Gdx.files.internal(textura));
	}
	
	public static TextureRegion getSomSprite()
	{
		if (sons)
			return som_ligado;
		else
			return som_desligado;
	}
	
	public static void carregarTudo()
	{
		sons = true;
		gramado = carregarTextura("gramado.png");
		fundoMenuPrincipal = carregarTextura("fundo_menu.png");
		musica = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		musica.setLooping(true);
		musica.setVolume(0.5f);
		musica.play();
		sprites = carregarTextura("sprites.png");
		lixeira_vermelha = new TextureRegion(sprites, 0, 0, 80, 100);
		lixeira_verde = new TextureRegion(sprites, 80, 0, 80, 100);
		lixeira_amarela = new TextureRegion(sprites, 160, 0, 80, 100);
		lixeira_azul = new TextureRegion(sprites, 240, 0, 80, 100);
		estilingue_tras = new TextureRegion(sprites, 0, 100, 100, 230);
		estilingue_frente = new TextureRegion(sprites, 100, 100, 48, 57);
		garrafa_heineken = new TextureRegion(sprites, 148, 100, 32, 48);
		texto_iniciar = new TextureRegion(sprites, 180, 100, 120, 40);
		titulo = new TextureRegion(sprites, 100, 157, 128, 70);
		som_ligado = new TextureRegion(sprites, 180, 140, 16, 16);
		som_desligado = new TextureRegion(sprites, 196, 140, 16, 16);
		
	}
	
	// odeio nomes gigantes. não sou javeiro então foda-se
	// btw camel-case é a salvação
	public static void alternarSomOnOff()
	{
		sons = !sons;
		if (musica.isPlaying()) 
			musica.pause();
		else 
			musica.play();
	}
}
