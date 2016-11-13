package com.lixo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projetil
{
    public static enum Tipo
    {
        PLASTICO,
        VIDRO,
        PAPEL,
        METAL
    }
    
    public static enum Estado
    {
        PARADO,
        LANCADO
    }
    
    TextureRegion sprite;
    public Vector2 posicao;
    public Vector2 velocidade;
    public Estado estado;
    public final Rectangle area;
    Mundo mundo;
    
    public Projetil(Tipo tipo, Mundo mundo, float x, float y)
    {
        this.posicao = new Vector2(x, y);
        this.mundo = mundo;
        velocidade = new Vector2();
        area = new Rectangle();
        estado = Estado.PARADO;
        switch (tipo)
        {
        case PLASTICO:
            sprite = Assets.garrafa_heineken;
            break;
            
        case VIDRO:
            break;
            
        case PAPEL:
            break;
            
        case METAL:
            break;
            
        default:
            throw new IllegalArgumentException("Projetil de tipo invalido");
        }
    }
    
    public void lancar(Vector2 aceleracao)
    {
        velocidade = aceleracao;
        estado = Estado.LANCADO;
    }
    
    public void atualizar(float tempo_delta)
    {
        if (estado == Estado.LANCADO) 
        {
            velocidade.add(velocidade.x * tempo_delta * Mundo.gravidade.x, 
                           velocidade.y * tempo_delta * Mundo.gravidade.y);
            
            posicao.add(velocidade.x * tempo_delta,
                        velocidade.y * tempo_delta);
        }
        
        area.x = posicao.x - area.width / 2;
        area.y = posicao.y - area.height / 2;
    }
    
    public void desenhar(Batch batch)
    {
        batch.draw(sprite, posicao.x, posicao.y);
    }
}
