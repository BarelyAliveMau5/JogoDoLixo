package com.lixo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lixo.Projetil.Tipo;

public class Lixeira
{
    public static enum Cor
    {
        VERMELHA,
        VERDE,
        AZUL,
        AMARELA
    }
    
    public final Cor cor;
    final Vector2 posicao;
    final Rectangle boca;
    
    public Lixeira(Cor cor, float x, float y)
    {
        posicao = new Vector2(x, y);
        this.cor = cor;
        boca = new Rectangle(x+11, y+72, 58, 24);
    }
    
    public boolean testarLixo(Vector2 posicao, Tipo tipo)
    {
        if (boca.contains(posicao))
        {
            switch (tipo)
            {
            case PLASTICO:
                if (cor == Cor.VERMELHA)
                    return true;
                break;
            case METAL:
                if (cor == Cor.AMARELA)
                    return true;
                break;
            case PAPEL:
                if (cor == Cor.AZUL)
                    return true;
                break;
            case VIDRO:
                if (cor == Cor.VERDE)
                    return true;
                break;
            default:
                return false;
            }
        }
        return false;
    }
    
    TextureRegion getTextureRegionDaLixeira(Cor cor)
    {
        switch(cor)
        {
        case VERMELHA:
            return Assets.lixeira_vermelha;
        case VERDE:
            return Assets.lixeira_verde;
        case AZUL:
            return Assets.lixeira_azul;
        case AMARELA:
            return Assets.lixeira_amarela;
        }
        return null;
    }
    
    public void desenhar(Batch batch)
    {
        batch.draw(getTextureRegionDaLixeira(cor), posicao.x, posicao.y);
    }
}
