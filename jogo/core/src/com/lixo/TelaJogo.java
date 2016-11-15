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


public class TelaJogo extends ScreenAdapter
{

	OrthographicCamera camera;
	Lixo jogo;
	BotaoSimples btnSom;
	BotaoSimples btnMusica;
	BotaoSimples btnSair;
	Vector3 areaDoClick;
	Array<Projetil> projeteis;
	int chances;
	Mundo mundo;
	float tempo_delta;
	
	float teste_rot;
	Vector2 aceleracao;
	Tipo teste_tipo;
	
	public TelaJogo(Lixo jogo)
	{
		this.jogo = jogo;
		camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
		camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
		mundo = new Mundo();
		btnSom = new BotaoSimples(Assets.getSomTextureRegion(), Assets.TELA_LARGURA -16, Assets.TELA_ALTURA -16, 16, 16);
		btnMusica = new BotaoSimples(Assets.getMusicaTextureRegion(), Assets.TELA_LARGURA -32, Assets.TELA_ALTURA -16, 16, 16);
		btnSair = new BotaoSimples(Assets.btn_sair, 0, Assets.TELA_ALTURA -16, 16, 16);
		areaDoClick = new Vector3();
		aceleracao = new Vector2(4f,7f);
		projeteis = new Array<Projetil>();
		chances = 5;
		
		teste_tipo = Tipo.METAL;
	}
	
	//somente usado pra testes
	public void testes_debug()
	{
	    //opções para debugging. divirta-se vendo o iphone ser lançado no lixo
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            aceleracao.x += 0.1f;
            System.out.println("accel:x=" + aceleracao.x +", y=" + aceleracao.y);
        }
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            aceleracao.x -= 0.1f;
            System.out.println("accel:x=" + aceleracao.x +", y=" + aceleracao.y);
        }
        
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            aceleracao.y -= 0.1f;
            System.out.println("accel:x=" + aceleracao.x +", y=" + aceleracao.y);
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.NUM_1)){
            teste_tipo = Tipo.VIDRO;
            System.out.println("tipo = vidro");
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
            teste_tipo = Tipo.PLASTICO;
            System.out.println("tipo = plastico");
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.NUM_3)){
            teste_tipo = Tipo.PAPEL;
            System.out.println("tipo = papel");
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.NUM_4)){
            teste_tipo = Tipo.METAL;
            System.out.println("tipo = metal");
        }
        
        if(Gdx.input.isKeyPressed(Keys.HOME)){
            Mundo.gravidade.y += 0.1f;
            System.out.println("+gravidade Y:" + Mundo.gravidade.toString());
        }
        
        if(Gdx.input.isKeyPressed(Keys.END)){
            Mundo.gravidade.y -= 0.1f;
            System.out.println("-gravidade Y:" + Mundo.gravidade.toString());
        }
        
        if(Gdx.input.isKeyPressed(Keys.INSERT)){
            Mundo.gravidade.x -= 0.1f;
            System.out.println("-gravidade X:" + Mundo.gravidade.toString());
        }
        
        if(Gdx.input.isKeyPressed(Keys.PAGE_DOWN)){
            Mundo.gravidade.x += 0.1f;
            System.out.println("+gravidade X:" + Mundo.gravidade.toString());
        }
        
        if(Gdx.input.isKeyPressed(Keys.D)){
            if (projeteis.size > 0){
                System.out.println("deletado idx " + projeteis.size);
                projeteis.removeIndex(0);
            }
        }
        
        if(Gdx.input.isKeyPressed(Keys.A)){
            if (projeteis.size > 0){
                System.out.println("deletado tudo: " + projeteis.size);
                projeteis.clear();
            }
        }
        
        if(Gdx.input.isKeyPressed(Keys.Z)){
            teste_rot -= 0.1f;
            System.out.println("rotacao: " + teste_rot);
        }
        
        if(Gdx.input.isKeyPressed(Keys.X)){
            teste_rot += 0.1f;
            System.out.println("rotacao: " + teste_rot);
        }
        
        if(Gdx.input.isKeyJustPressed(Keys.G)){
            System.gc();
            System.out.println("Garbage Collection");
        }
        
        if(Gdx.input.isKeyPressed(Keys.UP)){
            aceleracao.y += 0.1f;
            System.out.println("accel:x=" + aceleracao.x +", y=" + aceleracao.y);
        }
        if(Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.SPACE)) {
            camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            Projetil teste = new Projetil(teste_tipo, 0, 0);
            teste.velocidade.x = 0;
            teste.velocidade.y = 0;
            teste.posicao.x = areaDoClick.x;
            teste.posicao.y = areaDoClick.y;
            teste.lancar(aceleracao);
            projeteis.add(teste);
        }
	}
	
	public void atualizar()
	{
	    mundo.checarLimites(projeteis);
	    if(Gdx.input.justTouched()) 
        {
	        camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));
	        if(btnSom.checar_click(areaDoClick))
            {
                Assets.alternarSomOnOff();
                btnSom.setImagem(Assets.getSomTextureRegion());
                Assets.tocarSom(Assets.som_botao_click);
                return;
            }
            
            if(btnMusica.checar_click(areaDoClick))
            {
                Assets.alternarMusicaOnOff();
                btnMusica.setImagem(Assets.getMusicaTextureRegion());
                Assets.tocarSom(Assets.som_botao_click);
                return;
            }
            if(btnSair.checar_click(areaDoClick))
            {
                Assets.tocarSom(Assets.som_botao_click);
                jogo.setScreen(new MenuPrincipal(jogo));
                return;
            }
        }
	    if (Assets.debug)
	        testes_debug();
	}
	
	void desenhar_projeteis(float delta)
	{
	    for(Projetil projetil : projeteis)
            projetil.desenhar(jogo.batch, delta);
	}
	
	void desenhar_anim_grama(float delta, int y, int w, int h)
	{
	    //tempo_delta += delta*5;
	    for (int k=0; k<h; k++)
            for (int j=0; j<w; j++)
                jogo.batch.draw(Assets.grama.getKeyFrame((tempo_delta+j+k*3),true),-50+j*70+k*10,y+30*k,80,80);
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
        jogo.fonte.draw(jogo.batch, "Chances: " + chances,Assets.TELA_LARGURA / 2 -50,Assets.TELA_ALTURA - 5);
        btnSom.desenhar(jogo.batch);
        btnMusica.desenhar(jogo.batch);
        btnSair.desenhar(jogo.batch);
        jogo.batch.draw(Assets.gramado, 0, 0, Assets.TELA_LARGURA, 200);
        desenhar_anim_grama(delta,40,10,4);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.05f-10,Assets.TELA_ALTURA * 0.1f-10,120,40);
        jogo.batch.draw(Assets.estilingue_tras, Assets.TELA_LARGURA * 0.05f, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.estilingue_frente, Assets.TELA_LARGURA * 0.05f, Assets.TELA_ALTURA * 0.1f + 130 - 57);
        /*
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.9f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_vermelha, Assets.TELA_LARGURA * 0.9f - 50, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.78f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_verde, Assets.TELA_LARGURA * 0.78f - 50, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.66f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_azul, Assets.TELA_LARGURA * 0.66f - 50, Assets.TELA_ALTURA * 0.1f);
        jogo.batch.draw(Assets.sombra, Assets.TELA_LARGURA * 0.54f - 75,Assets.TELA_ALTURA * 0.1f-10,140,40);
        jogo.batch.draw(Assets.lixeira_amarela, Assets.TELA_LARGURA * 0.54f - 50, Assets.TELA_ALTURA * 0.1f);
        */
        desenhar_projeteis(delta);
        desenhar_anim_grama(delta,-20,10,2);
        //for (int k=0; k<2; k++)
        //    for (int j=0; j<10; j++)
        //        jogo.batch.draw(Assets.grama.getKeyFrame((tempo_delta+j+k*3),true),-50+j*70+k*10,-20+30*k,80,80);
        jogo.batch.end();
	}
	
	@Override
	public void render (float delta) {
	    tempo_delta += delta*5;
		desenhar(delta);
		atualizar();
	}
	
	@Override
	public void hide()
	{
	   this.dispose();
	}
}
