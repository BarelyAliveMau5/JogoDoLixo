/*************************************************
 * tela do jogo em si
 *************************************************/

package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class TelaJogo extends ScreenAdapter
{

	OrthographicCamera camera;
	Lixo jogo;
	Rectangle rectSom;  //16x16
	Rectangle rectMusica;  //16x16
	Rectangle rectPausar;  //16x16
	float tempo_delta;
	
	public  TelaJogo(Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
	}
	
	public void desenhar (float delta) 
	{
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        jogo.batch.setProjectionMatrix(camera.combined);
        jogo.batch.begin();
        jogo.batch.draw(Assets.fundoJogo, 0, 160, Assets.TELA_LARGURA, Assets.TELA_ALTURA);
        jogo.batch.draw(Assets.gramado, 0, 0, Assets.TELA_LARGURA, 200);
        for (int k=0; k<6; k++)
            for (int j=0; j<10; j++)
                jogo.batch.draw(Assets.grama.getKeyFrame((delta+j+k*3),true),-50+j*70+k*10,-20+30*k,80,80);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.05f-10,Assets.TELA_ALTURA * 0.1f-10,120,40);
        jogo.batch.draw(Assets.estilingue_tras, Assets.TELA_LARGURA * 0.05f, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.estilingue_frente, Assets.TELA_LARGURA * 0.05f, Assets.TELA_ALTURA * 0.1f + 130 - 57);
        jogo.batch.end();
	}
	
	@Override
	public void render (float delta) {
	    tempo_delta += delta * 5;
		desenhar(tempo_delta);
	}
}
