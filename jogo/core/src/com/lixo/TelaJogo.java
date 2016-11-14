/*************************************************
 * tela do jogo em si
 *************************************************/

package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.lixo.Projetil.Tipo;

import sun.net.www.content.text.PlainTextInputStream;

public class TelaJogo extends ScreenAdapter
{

	OrthographicCamera camera;
	Lixo jogo;
	Rectangle rectSom;  //16x16
	Rectangle rectMusica;  //16x16
	Rectangle rectSair;  //16x16
	Vector3 areaDoClick;
	Array<Projetil> projeteis;
	int chances;
	Mundo mundo;
	float tempo_delta;
	
	Projetil teste;
	Vector2 aceleracao;
	
	public TelaJogo(Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
		mundo = new Mundo();
		areaDoClick = new Vector3();
		teste = new Projetil(Tipo.PLASTICO, Assets.TELA_LARGURA * 0.05f,Assets.TELA_ALTURA * 0.2f);
		aceleracao = new Vector2(1f,1f);
		chances = 5;
	}
	
	public void atualizar()
	{
	    mundo.checarLimites(teste);
	    if(Gdx.input.justTouched()) 
        {
	        camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));
	        teste.velocidade.x = 0;
	        teste.velocidade.y = 0;
	        teste.posicao.x = areaDoClick.x;
	        teste.posicao.y = areaDoClick.y;
	        teste.tipo = Tipo.METAL;
	        teste.lancar(aceleracao);
	        
        }
	    
	    //opções para debugging. divirta-se vendo o iphone ser lançado no lixo
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
	        aceleracao.x += 0.1f;
	        System.out.println("accel:" + aceleracao.x +"," + aceleracao.y);
	    }
	    
	    if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	        aceleracao.x -= 0.1f;
	        System.out.println("accel:" + aceleracao.x +"," + aceleracao.y);
	    }
	    
	    if(Gdx.input.isKeyPressed(Keys.DOWN)){
            aceleracao.y -= 0.1f;
            System.out.println("accel:" + aceleracao.x +"," + aceleracao.y);
	    }
	    
	    if(Gdx.input.isKeyPressed(Keys.UP)){
	        aceleracao.y += 0.1f;
	        System.out.println("accel:" + aceleracao.x +"," + aceleracao.y);
	    }
	    
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
        jogo.fonte.draw(jogo.batch, "Chances: " + chances,5,Assets.TELA_ALTURA - 5);
        jogo.batch.draw(Assets.gramado, 0, 0, Assets.TELA_LARGURA, 200);
        tempo_delta += delta*5;
        for (int k=0; k<4; k++)
            for (int j=0; j<10; j++)
                jogo.batch.draw(Assets.grama.getKeyFrame((tempo_delta+j+k*3),true),-50+j*70+k*10,40+30*k,80,80);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.05f-10,Assets.TELA_ALTURA * 0.1f-10,120,40);
        jogo.batch.draw(Assets.estilingue_tras, Assets.TELA_LARGURA * 0.05f, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.estilingue_frente, Assets.TELA_LARGURA * 0.05f, Assets.TELA_ALTURA * 0.1f + 130 - 57);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.9f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_vermelha, Assets.TELA_LARGURA * 0.9f - 50, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.78f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_verde, Assets.TELA_LARGURA * 0.78f - 50, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.66f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_azul, Assets.TELA_LARGURA * 0.66f - 50, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.54f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_amarela, Assets.TELA_LARGURA * 0.54f - 50, Assets.TELA_ALTURA * 0.1f);
        teste.desenhar(jogo.batch, delta);
        for (int k=0; k<2; k++)
            for (int j=0; j<10; j++)
                jogo.batch.draw(Assets.grama.getKeyFrame((tempo_delta+j+k*3),true),-50+j*70+k*10,-20+30*k,80,80);
        jogo.batch.end();
	}
	
	@Override
	public void render (float delta) {
		desenhar(delta);
		atualizar();
	}
}
