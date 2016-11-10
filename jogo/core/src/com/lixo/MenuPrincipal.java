package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuPrincipal extends ScreenAdapter
{	
	Lixo jogo;
	OrthographicCamera camera;
	Rectangle btnArea_Jogar;	//120x40
	Rectangle btnArea_Musica;	//40x40
	Vector3 areaDoClick;
	
	public MenuPrincipal (Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
		btnArea_Jogar = new Rectangle(Assets.TELA_LARGURA/2 - 60, Assets.TELA_ALTURA/2 - 20, 120, 40);
		btnArea_Musica = new Rectangle(Assets.TELA_LARGURA - 20, Assets.TELA_ALTURA - 20, 40, 40);
		areaDoClick = new Vector3();
	}
	
	public void atualizar()
	{
		//funciona com o click do mouse. na verdade não existe um evento de click '-'
		if(Gdx.input.justTouched()) 
		{
			camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if(btnArea_Jogar.contains(areaDoClick.x, areaDoClick.y))
				jogo.setScreen(new TelaJogo(jogo));
		}
	}
	
	public void desenhar()
	{
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		jogo.batch.setProjectionMatrix(camera.combined);
		
		jogo.batch.enableBlending();
		jogo.batch.begin();
		jogo.batch.draw(Assets.fundoMenuPrincipal, 0, 0, 640, 480);
		jogo.batch.draw(Assets.texto_iniciar, Assets.TELA_LARGURA/2-60, Assets.TELA_ALTURA/2-20);
		jogo.batch.end();
	}
	
	@Override
	public void render (float delta) 
	{
		atualizar();
		desenhar();
	}
}
