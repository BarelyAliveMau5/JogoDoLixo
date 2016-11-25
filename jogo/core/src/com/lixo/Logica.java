package com.lixo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.lixo.Projetil.TipoProjetil;

public class Logica
{
    public static enum EstadoJogo
    {
        JOGANDO,
        VENCEU,
        PERDEU
    }
    
    public static int TOTAL_CHANCES = 5;
    int chances;
    int restante;
    public EstadoJogo estado;
    Array<TipoProjetil> tipos_de_lixos;
    TipoProjetil atual;
    int contador;
    
    
    public Logica()
    {
        tipos_de_lixos = new Array<TipoProjetil>();
        criar_projeteis();
        contador = 0;
        restante = 5;
        chances = TOTAL_CHANCES;
        estado = EstadoJogo.JOGANDO;
        atual = tipos_de_lixos.get(0);
        
    }
    
    void criar_projeteis()
    {
        for (TipoProjetil tipo : TipoProjetil.values()) {
            if (tipo != TipoProjetil.INVISIVEL) {
                tipos_de_lixos.add(tipo);
                tipos_de_lixos.add(tipo);
            }
        }
        
        RandomXS128 rnd = new RandomXS128();
        for (int i=0;i<tipos_de_lixos.size*2; i++) 
            tipos_de_lixos.swap(i % tipos_de_lixos.size, (int) (rnd.nextFloat() * tipos_de_lixos.size));
        for (int i=0;i<tipos_de_lixos.size; i++) 
            System.out.println(tipos_de_lixos.get(i).toString());
        
    }
    
    public void proximoProjetil()
    {
        atual = tipos_de_lixos.get(++contador % 8);
    }
    
    public TipoProjetil getProjetil()
    {
        return atual;
    }
    
    public TextureRegion getProjetilTextureRegion()
    {
        switch(atual)
        {
        case METAL:
            return Assets.metal_iphone;
        case PAPEL:
            return Assets.papel_aps;
        case PLASTICO:
            return Assets.plastico_shake;
        case VIDRO:
            return Assets.vidro_heineken;
        case INVISIVEL:
            break;
        default:
            break;
        }
        return Assets.borracha;
    }
    
    public int getChances()
    {
        return chances;
    }
    
    public int getRestante()
    {
        return restante;
    }
    
    public void errou(boolean rude)
    {
        if (chances > 0)
            chances--;
        else
            estado = EstadoJogo.PERDEU;
        if(rude)
            Assets.tocarSom(Assets.som_rude);
        else
            Assets.tocarSom(Assets.som_faustao);
    }
    
    public void acertou()
    {
        if (restante < 1)
            estado = EstadoJogo.VENCEU;
        restante--;
    }
    
}
