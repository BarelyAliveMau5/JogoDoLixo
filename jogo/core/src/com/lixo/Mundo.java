package com.lixo;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lixo.Lixeira.Cor;
import com.lixo.Logica.EstadoJogo;
import com.lixo.Projetil.TipoProjetil;

public class Mundo
{
    public static final Vector2 gravidade = new Vector2(-1f, -30f);
    public static final float INTERVALO_LANCAMENTO = 3f;
    public Logica logica_do_jogo;
    public Vector2 aceleracao;
    Rectangle limite_chao;
    Rectangle limite_parede;
    Lixo jogo;
    Array<Lixeira> lixeiras;
    Projetil projetil;
    Array<Projetil> projeteis;
    float ultimo_lance;
    
    public Mundo(Lixo jogo)
    {
        projetil = new Projetil(TipoProjetil.INVISIVEL, 0,0);
        projeteis = new Array<Projetil>();
        aceleracao = new Vector2(1,1);
        this.jogo = jogo;
        ultimo_lance = 5f;
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
                    if (projetil.tipo == TipoProjetil.VIDRO)
                        Assets.tocarSom(Assets.som_vidro);
                    else
                        Assets.tocarSom(Assets.som_lixo);
                    projetil.desativar();
                    projetil.desaparecer();
                }
            }
        }
    }
    
    public double getForca()
    {
        return Math.sqrt(aceleracao.y * aceleracao.y + aceleracao.x * aceleracao.x);
    }
    
    public double getAngulo()
    {
        return Math.toDegrees(Math.atan2(aceleracao.y, aceleracao.x));
    }
    
    boolean proximoLancePossivel()
    {
        ultimo_lance = ultimo_lance + Gdx.graphics.getDeltaTime();
        if (ultimo_lance > INTERVALO_LANCAMENTO)
            return true;
        else
            return false;
    }
    
    public void lancarProjetil()
    {
        if (proximoLancePossivel())
        {
            ultimo_lance = 0f;
            projeteis.add(new Projetil(logica_do_jogo.getProjetil(),
                    Assets.TELA_LARGURA * 0.15f,
                    Assets.TELA_ALTURA * 0.32f));
            
            projeteis.peek().lancar(aceleracao);
            /* projetil = new Projetil(logica_do_jogo.getProjetil(),
                    Assets.TELA_LARGURA * 0.15f,
                    Assets.TELA_ALTURA * 0.32f);
            projetil.lancar(aceleracao); */
            logica_do_jogo.proximoProjetil();
        }
    }
    
    public void setAngulo(boolean aumentar)
    {
        double forca = getForca();
        double angulo = getAngulo();
        if (angulo > 39)
            angulo = 39;
        if (angulo < 11)
            angulo = 11;
        double tetha = Math.toRadians(aumentar? angulo-1 : angulo+1);
        aceleracao.x = (float) (forca* Math.cos(tetha));
        aceleracao.y = (float) (forca* Math.sin(tetha));
    }
    
    public void setForca(boolean aumentar)
    {
        double forca = aumentar ? getForca()+0.1f : getForca()-0.1f;
        double tetha = Math.toRadians(getAngulo());
        if (forca > 20)
            forca = 20;
        if (forca < 5)
            forca = 5;
        aceleracao.x = (float) (forca* Math.cos(tetha));
        aceleracao.y = (float) (forca* Math.sin(tetha));
    }
    
    public void atualizar()
    {
        checarLimites(projeteis);
        proximoLancePossivel();
        if(logica_do_jogo.estado != EstadoJogo.JOGANDO)
            jogo.setScreen(new Fim(jogo, logica_do_jogo.estado));
    }
    
    public void desenhar(Batch batch)
    {
        for (Lixeira lixeira : lixeiras)
            lixeira.desenhar(batch);
        float angulo = (float) getAngulo();
        /* aviso de codigo mega gambiarra */
        batch.draw(Assets.seta_ponta, aceleracao.x *10 + Assets.TELA_LARGURA * 0.125f , aceleracao.y *10+Assets.TELA_ALTURA * 0.25f,
                0f, 8f, 16f, 16f, 2f, 2f, angulo);
        batch.draw(Assets.borracha, Assets.TELA_LARGURA * 0.15f , Assets.TELA_ALTURA * 0.32f,
                0f, 8f, (float) -getForca() *4f, 8f, 1f, 1f, angulo);
        batch.draw(logica_do_jogo.getProjetilTextureRegion(), 
                (float) -getForca() *4.2f +  Assets.TELA_LARGURA * 0.15f -16f, 
                (float) -getForca() *3f + Assets.TELA_ALTURA * 0.32f,
                Assets.TELA_LARGURA * 0.15f-32,
                Assets.TELA_ALTURA * 0.3f-64,32,48,1,1,angulo-30f);
        batch.draw(Assets.borracha, Assets.TELA_LARGURA * 0.08f , Assets.TELA_ALTURA * 0.32f,
                0f, 8f, (float) -getForca() *3f, 8f, 1f, 1f, angulo *1.3f + 10f);
        /* fim da super gambiarra */
        for(Projetil projetil : projeteis)
            projetil.desenhar(jogo.batch, Gdx.graphics.getDeltaTime());
    }
    
}
