package com.lixo;


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
    public int restante; //quanto falta pra ganhar
    public EstadoJogo estado;
    
    public Logica()
    {
        chances = TOTAL_CHANCES;
        restante = 8;
        estado = EstadoJogo.JOGANDO;
    }
    
    public int getChances()
    {
        return chances;
    }
    
    public int getRestante()
    {
        return restante;
    }
    
    //DRY?
    void processa(Integer oq, EstadoJogo acontece)
    {
        if (oq > 0)
            oq--;
        else
            estado = acontece;
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
        if (restante > 0)
            restante--;
        else
            estado = EstadoJogo.VENCEU;
    }
    
}
