/*************************************************
 * o nome ja diz..
 *************************************************/

package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MenuPrincipal extends ScreenAdapter
{	
	Lixo jogo;
	OrthographicCamera camera;
	BotaoSimples btnJogar;  //120x40
	BotaoSimples btnAjuda;  //120x40
	BotaoSimples btnSom;  //16x16
	BotaoSimples btnMusica;  //16x16
	Vector3 areaDoClick;
	
	public MenuPrincipal (Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
		btnJogar = new BotaoSimples(Assets.txt_iniciar, Assets.TELA_LARGURA/2 - 60, Assets.TELA_ALTURA/2 - 20, 120, 40);
		btnAjuda = new BotaoSimples(Assets.txt_ajuda,Assets.TELA_LARGURA/2 - 60, Assets.TELA_ALTURA/2 - 60, 120, 40);
		btnSom = new BotaoSimples(Assets.getSomTextureRegion(), Assets.TELA_LARGURA -16, Assets.TELA_ALTURA -16, 16, 16);
		btnMusica = new BotaoSimples(Assets.getMusicaTextureRegion(), Assets.TELA_LARGURA -32, Assets.TELA_ALTURA -16, 16, 16);
		areaDoClick = new Vector3();
	}
	
	//verifica se foi um click em um dos botoes
	public void atualizar()
	{
		//funciona com o click do mouse. na verdade não existe um evento de click '-'
		if(Gdx.input.justTouched()) 
		{
			camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if(btnJogar.checar_click(areaDoClick))
			{
				jogo.setScreen(new TelaJogo(jogo));
                Assets.tocarSom(Assets.som_botao_click);
			}
			
			if(btnAjuda.checar_click(areaDoClick))
			{
			    jogo.setScreen(new TelaAjuda(jogo));
			    Assets.tocarSom(Assets.som_botao_click);
			}
			
			if(btnSom.checar_click(areaDoClick))
			{
			    Assets.alternarSomOnOff();
			    btnSom.setImagem(Assets.getSomTextureRegion());
			    Assets.tocarSom(Assets.som_botao_click);
            }
			
			if(btnMusica.checar_click(areaDoClick))
			{
				Assets.alternarMusicaOnOff();
				btnMusica.setImagem(Assets.getMusicaTextureRegion());
                Assets.tocarSom(Assets.som_botao_click);
			}
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
		btnJogar.desenhar(jogo.batch);
		btnAjuda.desenhar(jogo.batch);
		btnSom.desenhar(jogo.batch);
		btnMusica.desenhar(jogo.batch);
		jogo.batch.draw(Assets.titulo, Assets.TELA_LARGURA/2-60, Assets.TELA_ALTURA - 100);
		jogo.batch.end();
	}
	
	@Override
	public void render (float delta) 
	{
		atualizar();
		desenhar();
	}
}
