/*************************************************
 * o nome ja diz..
 *************************************************/

package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class TelaAjuda extends ScreenAdapter
{
    OrthographicCamera camera;
    Rectangle rectVoltar;
    Vector3 areaDoClick;
    Lixo jogo;
    
    public  TelaAjuda(Lixo jogo)
    {
        this.jogo = jogo;
        camera = new OrthographicCamera(Assets.TELA_LARGURA, Assets.TELA_ALTURA);
        camera.position.set(Assets.TELA_LARGURA / 2, Assets.TELA_ALTURA / 2, 0);
        areaDoClick = new Vector3();
        rectVoltar = new Rectangle(10, 10, 120, 40);
    }
    
    public void atualizar()
    {
        if(Gdx.input.justTouched()) 
        {
            camera.unproject(areaDoClick.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(rectVoltar.contains(areaDoClick.x, areaDoClick.y))
            {
                jogo.setScreen(new MenuPrincipal(jogo));
                Assets.botao_click.play();
            }
        }
    }
    
    public void desenhar () 
    {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        jogo.batch.setProjectionMatrix(camera.combined);
        jogo.batch.begin();
        jogo.batch.draw(Assets.fundoMenuPrincipal, 0, 0, Assets.TELA_LARGURA, Assets.TELA_ALTURA);
        jogo.batch.draw(Assets.txt_voltar, 10, 10);
        jogo.batch.draw(Assets.titulo_ajuda, Assets.TELA_LARGURA/2-76, Assets.TELA_ALTURA - 100);
        jogo.fonte.draw(jogo.batch, "Ajuste o ângulo e força do estilingue\npara acertar o objeto na lixeira certa", (float) (Assets.TELA_LARGURA * 0.1), (float) (Assets.TELA_ALTURA - Assets.TELA_ALTURA * 0.25));
        jogo.batch.end();
    }
    
    @Override
    public void render (float delta) {
        atualizar();
        desenhar();
    }
}
