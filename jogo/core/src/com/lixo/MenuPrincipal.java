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
	Rectangle rectJogar;  //120x40
	Rectangle rectAjuda;  //120x40
	Rectangle rectSom;  //16x16
	Vector3 areaDoClick;
	
	public MenuPrincipal (Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
		rectJogar = new Rectangle(Assets.TELA_LARGURA/2 - 60, Assets.TELA_ALTURA/2 - 20, 120, 40);
		rectAjuda = new Rectangle(Assets.TELA_LARGURA/2 - 60, Assets.TELA_ALTURA/2 - 60, 120, 40);
		rectSom = new Rectangle(Assets.TELA_LARGURA -16, Assets.TELA_ALTURA -16, 16, 16);
		areaDoClick = new Vector3();
	}
	
	public void atualizar()
	{
		//funciona com o click do mouse. na verdade não existe um evento de click '-'
		if(Gdx.input.justTouched()) 
		{
			camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if(rectJogar.contains(areaDoClick.x, areaDoClick.y))
			{
				jogo.setScreen(new TelaJogo(jogo));
				Assets.botao_click.play();
			}
			
			if(rectAjuda.contains(areaDoClick.x, areaDoClick.y))
			{
			    jogo.setScreen(new TelaAjuda(jogo));
			    Assets.botao_click.play();
			}
			
			if(rectSom.contains(areaDoClick.x, areaDoClick.y))
				Assets.alternarSomOnOff();
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
		jogo.batch.draw(Assets.fundoMenuPrincipal, 0, 0, Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		jogo.batch.draw(Assets.txt_iniciar, Assets.TELA_LARGURA/2-60, Assets.TELA_ALTURA/2-20);
		jogo.batch.draw(Assets.txt_ajuda, Assets.TELA_LARGURA/2-60, Assets.TELA_ALTURA/2-60);
		jogo.batch.draw(Assets.titulo, Assets.TELA_LARGURA/2-60, Assets.TELA_ALTURA - 100);
		jogo.batch.draw(Assets.getSomSprite(), Assets.TELA_LARGURA-16, Assets.TELA_ALTURA-16);
		jogo.batch.end();
	}
	
	@Override
	public void render (float delta) 
	{
		atualizar();
		desenhar();
	}
}
