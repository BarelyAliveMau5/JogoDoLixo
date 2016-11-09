package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuPrincipal extends ScreenAdapter
{
	private final int TELA_LARGURA = 640;
	private final int TELA_ALTURA = 480;
	Lixo jogo;
	OrthographicCamera camera;
	Rectangle btnArea_Jogar;
	Rectangle btnArea_Musica;
	Vector3 click;
	
	public MenuPrincipal (Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(TELA_LARGURA, TELA_ALTURA);
		camera.position.set(TELA_LARGURA / 2, TELA_ALTURA / 2, 0);
		btnArea_Jogar = new Rectangle(TELA_LARGURA/2 - 60, TELA_ALTURA/2 - 20, 120, 40);
		btnArea_Musica = new Rectangle(TELA_LARGURA - 20, TELA_ALTURA - 20, 40, 40);
		click = new Vector3();
	}
	
	public void atualizar()
	{
		//funciona com o click do mouse. na verdade não existe um evento de click '-'
		if(Gdx.input.justTouched())
			return;
	}
	
	public void desenhar()
	{
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);
		
		jogo.batch.disableBlending();
		jogo.batch.begin();
		jogo.batch.draw(Assets.fundoMenuPrincipal, 0, 0, 640, 480);
		jogo.batch.end();
	}
	
	@Override
	public void render (float delta) 
	{
		desenhar();
	}
}
