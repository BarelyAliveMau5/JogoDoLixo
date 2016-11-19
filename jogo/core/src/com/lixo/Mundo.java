package com.lixo;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lixo.Lixeira.Cor;
import com.lixo.Logica.EstadoJogo;
import com.lixo.Projetil.Tipo;

public class Mundo
{
    public static final Vector2 gravidade = new Vector2(-1f, -30f);
    public Logica logica_do_jogo;
    Rectangle limite_chao;
    Rectangle limite_parede;
    Lixo jogo;
    Array<Lixeira> lixeiras;
    
    public Mundo(Lixo jogo)
    {
        this.jogo = jogo;
        logica_do_jogo = new Logica();
        limite_chao = new Rectangle(0,-60,Assets.TELA_LARGURA+100,100);
        limite_parede = new Rectangle(Assets.TELA_LARGURA -10,0 ,100,Assets.TELA_ALTURA*2);
        lixeiras = new Array<Lixeira>();
        Array<Vector2> posicoes = new Array<Vector2>();
        
        for (float i=0.54f;i<0.91f; i+=0.12f)
            posicoes.add(new Vector2(Assets.TELA_LARGURA * i - 50, Assets.TELA_ALTURA * 0.1f));
        
        //sempre gerar lixeiras aleatorias. ponto extra pra uma coisa simples
        RandomXS128 rnd = new RandomXS128();
        for (int i=0;i<4; i++)
            posicoes.swap((int)(rnd.nextFloat() * 4), (int)(rnd.nextFloat()*4));
        
        lixeiras.add(new Lixeira(Cor.AMARELA, posicoes.pop()));
        lixeiras.add(new Lixeira(Cor.AZUL, posicoes.pop()));
        lixeiras.add(new Lixeira(Cor.VERDE, posicoes.pop()));
        lixeiras.add(new Lixeira(Cor.VERMELHA, posicoes.pop()));
    }
    
    public void checarLimites(Array<Projetil> projeteis)
    {
        boolean hit;
        for(Projetil projetil : projeteis)
        {
            hit = false;
            if (projetil.ativo) 
            {
                if ( (limite_chao.contains(projetil.posicao) 
                    || limite_parede.contains(projetil.posicao)))
                {
                    projetil.desativar();
                    hit = true;
                    logica_do_jogo.errou(false);
                    Assets.tocarSom(Assets.som_soco);
                    continue;
                    //System.out.println("gotcha x="+projetil.posicao.x + ", y="+projetil.posicao.y);
                }
                
                for(Lixeira lixeira : lixeiras)
                {
                    if (!hit) {
                        switch (lixeira.testarContatoLixo(projetil.posicao, projetil.tipo))
                        {
                        case ACERTOU:
                            logica_do_jogo.acertou();
                            hit = true;
                            break;
                        case ERROU:
                            hit = true;
                            logica_do_jogo.errou(true);
                            break;
                        case SEM_CONTATO:
                            hit = false;
                        default:
                            break;
                        }
                    }
                }
                
                if (hit)
                {
                    if (projetil.tipo == Tipo.VIDRO)
                        Assets.tocarSom(Assets.som_vidro);
                    else
                        Assets.tocarSom(Assets.som_lixo);
                    projetil.desativar();
                    projetil.desaparecer();
                }
            }
        }
    }
    
    public void atualizar()
    {
        if(logica_do_jogo.estado != EstadoJogo.JOGANDO)
            jogo.setScreen(new Fim(jogo, logica_do_jogo.estado));
    }
    
    public void desenhar(Batch batch)
    {
        for (Lixeira lixeira : lixeiras)
            lixeira.desenhar(batch);
    }
}
