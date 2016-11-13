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
    public Rectangle area;
    float rotacao;
    Mundo mundo;
    
    public Projetil(Tipo tipo, Mundo mundo, float x, float y)
    {
        this.posicao = new Vector2(x, y);
        this.mundo = mundo;
        velocidade = new Vector2();
        estado = Estado.PARADO;
        rotacao = 0;
        switch (tipo)
        {
        case PLASTICO:
            sprite = Assets.garrafa_heineken;
            area = new Rectangle(x, y, 32f, 48f);
            break;
            
        case VIDRO:

            area = new Rectangle();
            break;
            
        case PAPEL:

            area = new Rectangle();
            break;
            
        case METAL:

            area = new Rectangle();
            break;
            
        default:
            throw new IllegalArgumentException("Projetil de tipo invalido");
        }
    }
    
    public void lancar(Vector2 aceleracao)
    {
        velocidade.add(aceleracao);
        estado = Estado.LANCADO;
    }
    
    public void atualizar(float tempo_delta)
    {
        if (estado == Estado.LANCADO) 
        {
            velocidade.add(tempo_delta * Mundo.gravidade.x, 
                           tempo_delta * Mundo.gravidade.y); 
            
            posicao.add(velocidade.x ,
                        velocidade.y);
        }
        
        area.x = posicao.x - area.width / 2;
        area.y = posicao.y - area.height / 2;
    }
    
    public void desenhar(Batch batch, float delta)
    {
        atualizar(delta);
        if (estado == Estado.PARADO)
        {
            batch.draw(sprite, posicao.x, posicao.y);
            rotacao = 0;
        }
        else 
        {
            batch.draw(sprite, posicao.x, posicao.y, 
                    area.width/2, area.height /2, area.width, area.height, 1, 1, rotacao-=6);
        }
    }
}
