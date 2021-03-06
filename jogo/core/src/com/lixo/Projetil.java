package com.lixo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projetil
{
    public static enum TipoProjetil
    {
        PLASTICO,
        VIDRO,
        PAPEL,
        METAL,
        INVISIVEL
    }
    
    public Vector2 posicao;
    public Vector2 velocidade;
    public float accel;
    boolean ativo;
    public Rectangle area;
    public float rotacao;
    public TipoProjetil tipo;
    
    public Projetil(TipoProjetil tipo, float x, float y)
    {
        this.posicao = new Vector2(x, y);
        this.tipo = tipo;
        velocidade = new Vector2();
        ativo = false;
        rotacao = 0;
        //todos tem o mesmo tamanho
        area = new Rectangle(x, y, 32f, 48f);
    }
    
    TextureRegion getTipoSprite(TipoProjetil tipo)
    {
        switch (tipo)
        {
        case PLASTICO:
            return Assets.plastico_shake;
            
        case VIDRO:
            return Assets.vidro_heineken;
            
        case PAPEL:
            return Assets.papel_aps;
            
        case METAL:
            return Assets.metal_iphone;
            
        default:
            throw new IllegalArgumentException(); //se cagar no codigo ele caga em vc
        }
    }
    
    public void lancar(Vector2 aceleracao)
    {
        velocidade.add(aceleracao);
        ativo = true;
        accel = aceleracao.x;
        Assets.tocarSom(Assets.som_lancar);
    }
    
    public void atualizar(float tempo_delta)
    {
        if (ativo) 
        {
            //posição e velocidades são relativas ao intervalo de tempo entre atualizações
            velocidade.add(tempo_delta * Mundo.gravidade.x, 
                           tempo_delta * Mundo.gravidade.y); 
            posicao.add(velocidade.x, velocidade.y);
        }
        
        area.x = posicao.x - area.width / 2;
        area.y = posicao.y - area.height / 2;
    }
    
    public void desenhar(Batch batch, float delta)
    {
        if (tipo == TipoProjetil.INVISIVEL) 
            return;
        atualizar(delta);
        if (!ativo)
            //a quebra de linha mostra os parametros de rotação, não é separado do resto
            //foi dividido pelo açucar sintático/estética do codigo
            batch.draw(getTipoSprite(tipo), posicao.x, posicao.y,
                    area.width/2, area.height /2, area.width, area.height, 1, 1, rotacao);

        else 
            //btw se for um noob lendo: primeiro ele atribui a rotação com accel depois pega o valor
            batch.draw(getTipoSprite(tipo), posicao.x, posicao.y, 
                    area.width/2, area.height /2, area.width, area.height, 1, 1, rotacao-=accel);
    }
    
    public void desaparecer()
    {
        tipo = TipoProjetil.INVISIVEL;
    }
    
    public void desativar()
    {
        ativo = false;
        //tipo = Tipo.INVISIVEL;  //fica a criterio de quem quiser ver 
    }
}
