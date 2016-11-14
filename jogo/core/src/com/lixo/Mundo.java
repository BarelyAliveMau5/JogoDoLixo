package com.lixo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Mundo
{
    public static Vector2 gravidade = new Vector2(0, -12f);
    Rectangle limite_chao;
    Rectangle limite_parede;
    
    public Mundo()
    {
        limite_chao = new Rectangle(0,-60,Assets.TELA_LARGURA+100,100);
        limite_parede = new Rectangle(Assets.TELA_LARGURA -10,0 ,100,Assets.TELA_ALTURA*2);
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
                //projetil.posicao.set(-10,-10); // evitar que fique desativando sempre
            }
        }
    }
}
