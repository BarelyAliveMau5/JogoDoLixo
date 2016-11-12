/*************************************************
 * imagens e sons do jogo
 *************************************************/

package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets
{
    public static TextureRegion lixeira_vermelha;
    public static TextureRegion lixeira_verde;
    public static TextureRegion lixeira_amarela;
    public static TextureRegion lixeira_azul;
    public static TextureRegion estilingue_tras;
    public static TextureRegion estilingue_frente;
    public static TextureRegion txt_iniciar;
    public static TextureRegion txt_ajuda;
    public static TextureRegion txt_voltar;
    public static TextureRegion btn_pausar;
    public static TextureRegion titulo;
    public static TextureRegion titulo_ajuda;
    public static TextureRegion garrafa_heineken;
    public static Texture gramado;
    public static Texture sprites;
    public static Texture fundoMenuPrincipal;
    public static Animation grama;
    public static Music musica;
    public static Sound botao_click;
    static TextureRegion som_ligado;
    static TextureRegion som_desligado;
    static boolean sons;
    public static final int TELA_LARGURA = 640;
    public static final int TELA_ALTURA = 480;

    public static Texture carregarTextura(String textura)
    {
        return new Texture(Gdx.files.internal(textura));
    }

    public static TextureRegion getSomSprite()
    {
        if (sons)
            return som_ligado;
        else
            return som_desligado;
    }

    public static void carregarTudo()
    {
        sons = false; //mudar na dist
        gramado = carregarTextura("gramado.png");
        fundoMenuPrincipal = carregarTextura("fundo_menu.png");
        musica = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        musica.setLooping(true);
        musica.setVolume(0.5f);
        if (sons) musica.play();
        botao_click = Gdx.audio.newSound(Gdx.files.internal("botao_click.wav"));
        sprites = carregarTextura("sprites.png");
        lixeira_vermelha = new TextureRegion(sprites, 0, 0, 80, 100);
        lixeira_verde = new TextureRegion(sprites, 80, 0, 80, 100);
        lixeira_amarela = new TextureRegion(sprites, 160, 0, 80, 100);
        lixeira_azul = new TextureRegion(sprites, 240, 0, 80, 100);
        estilingue_tras = new TextureRegion(sprites, 0, 100, 100, 230);
        estilingue_frente = new TextureRegion(sprites, 100, 100, 48, 57);
        garrafa_heineken = new TextureRegion(sprites, 148, 100, 32, 48);
        txt_iniciar = new TextureRegion(sprites, 320, 0, 120, 40);
        txt_ajuda = new TextureRegion(sprites, 320, 40, 120, 40);
        txt_voltar = new TextureRegion(sprites, 320, 80, 120, 40);
        titulo = new TextureRegion(sprites, 100, 157, 128, 70);
        titulo_ajuda = new TextureRegion(sprites, 228, 170, 152, 64);
        som_ligado = new TextureRegion(sprites, 180, 140, 16, 16);
        som_desligado = new TextureRegion(sprites, 196, 140, 16, 16);
        btn_pausar = new TextureRegion(sprites, 212, 140, 16, 16);
        TextureRegion[] frames = new TextureRegion(new Texture("grass.png")).split(80, 120)[0];
        grama = new Animation(1, 
                frames[0],  frames[1],  frames[2],  frames[3],  frames[4], 
                frames[5],  frames[6],  frames[7],  frames[8],  frames[9], 
                frames[10], frames[11], frames[12], frames[13], frames[14], 
                frames[15], frames[16], frames[17], frames[18], frames[19], 
                frames[20], frames[21], frames[22], frames[23] ,frames[24]); 
    }

    // odeio nomes gigantes. não sou javeiro então foda-se
    // btw camel-case é a salvação
    public static void alternarSomOnOff()
    {
        sons = !sons;
        if (musica.isPlaying())
            musica.pause();
        else
            musica.play();
    }
}
