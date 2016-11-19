package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.lixo.Logica.EstadoJogo;

public class Fim extends ScreenAdapter
{
    OrthographicCamera camera;
    Lixo jogo;
    String msg;
    
    public Fim(Lixo jogo, EstadoJogo resultado)
    {
        this.jogo = jogo;
        camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
        camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
        if (resultado == EstadoJogo.PERDEU)
            this.msg = "Você perdeu!";
        else
            this.msg = "Você venceu!";
    }
    
    void atualizar()
    {
        if(Gdx.input.justTouched()) 
        {
            jogo.setScreen(new MenuPrincipal(jogo));
            Assets.tocarSom(Assets.som_botao_click);
        }
    }
    
    @Override
    public void render (float delta) {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        jogo.batch.begin();
        jogo.batch.draw(Assets.fundoMenuPrincipal, 0, 0, Assets.TELA_LARGURA, Assets.TELA_ALTURA);
        jogo.fonte.draw(jogo.batch, msg, Assets.TELA_LARGURA / 2 -80, Assets.TELA_ALTURA / 2 + 20);
        jogo.batch.end();
        atualizar();
    }
}
