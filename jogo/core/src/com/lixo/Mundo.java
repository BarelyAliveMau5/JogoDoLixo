package com.lixo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mundo
{
    public static final Vector2 gravidade = new Vector2(0, -12f);
    Rectangle limite_chao;
    Rectangle limite_parede;
    
    public Mundo()
    {
        limite_chao = new Rectangle(0,-100,Assets.TELA_LARGURA+100,100);
        limite_parede = new Rectangle(Assets.TELA_LARGURA ,0 ,100,100);
    }
    
    public void checarLimites(Projetil projetil)
    {
        if (limite_chao.contains(projetil.posicao) || limite_parede.contains(projetil.posicao))
        {
            projetil.desativar();
            System.out.println("gotcha "+projetil.posicao.x + ","+projetil.posicao.y);
            projetil.posicao.set(-10,-10); // evitar que fique desativando sempre
        }
    }
}
