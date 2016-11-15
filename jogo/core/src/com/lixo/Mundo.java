package com.lixo;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lixo.Lixeira.Cor;

public class Mundo
{
    
    
    public static final Vector2 gravidade = new Vector2(-1f, -30f);
    Rectangle limite_chao;
    Rectangle limite_parede;
    Array<Lixeira> lixeiras;
    
    public Mundo()
    {
        limite_chao = new Rectangle(0,-60,Assets.TELA_LARGURA+100,100);
        limite_parede = new Rectangle(Assets.TELA_LARGURA -10,0 ,100,Assets.TELA_ALTURA*2);
        lixeiras = new Array<Lixeira>();
        Array<Vector2> posicoes = new Array<Vector2>();
        
        for (float i=0.54f;i<0.91f; i+=0.12f)
            posicoes.add(new Vector2(Assets.TELA_LARGURA * i - 50, Assets.TELA_ALTURA * 0.1f));
            
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
        for(Projetil projetil : projeteis){
            if ( (limite_chao.contains(projetil.posicao) 
                || limite_parede.contains(projetil.posicao) )
                && projetil.ativo)
            {
                projetil.desativar();
                System.out.println("gotcha x="+projetil.posicao.x + ", y="+projetil.posicao.y);
            }
        }
    }
    
    public void desenhar(Batch batch)
    {
        for (Lixeira lixeira : lixeiras)
            lixeira.desenhar(batch);
    }
}
