package com.lixo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lixo extends Game {
	//melhor usar um objeto compartilhado
	public SpriteBatch batch;
	public BitmapFont fonte;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fonte = new BitmapFont(Gdx.files.internal("fonte.fnt"));
		Assets.carregarTudo();
		setScreen(new MenuPrincipal(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose() 
	{
	    batch.dispose();
	    fonte.dispose();
	}

}
