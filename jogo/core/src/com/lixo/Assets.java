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
    public static TextureRegion btn_sair;
    public static TextureRegion titulo;
    public static TextureRegion titulo_ajuda;
    public static TextureRegion vidro_heineken;
    public static TextureRegion plastico_shake;
    public static TextureRegion metal_iphone;
    public static TextureRegion papel_aps;
    public static TextureRegion sombra;
    public static Texture gramado;
    public static Texture sprites;
    public static Texture fundoMenuPrincipal;
    public static Texture fundoJogo;
    public static Animation grama;
    public static Music musica;
    public static Sound som_botao_click;
    public static Sound som_faustao;
    public static Sound som_rude;
    public static Sound som_vidro;
    public static Sound som_lixo;
    public static Sound som_soco;
    public static Sound som_lancar;
    static TextureRegion musica_ligada;
    static TextureRegion som_ligado;
    static TextureRegion musica_desligada;
    static TextureRegion som_desligado;
    static boolean tocar_sons;
    static boolean tocar_musica;
    public static final int TELA_LARGURA = 640;
    public static final int TELA_ALTURA = 480;

    public static void carregarTudo()
    {
        tocar_musica = true; //mudar na dist
        tocar_sons = true;
        gramado = carregarTextura("gramado.png");
        fundoMenuPrincipal = carregarTextura("fundo_menu.png");
        fundoJogo = carregarTextura("fundo.png");
        musica = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        musica.setLooping(true);
        musica.setVolume(0.5f);
        if (tocar_musica) musica.play();
        som_botao_click = Gdx.audio.newSound(Gdx.files.internal("botao_click.ogg"));
        som_faustao = Gdx.audio.newSound(Gdx.files.internal("errou.ogg"));
        som_rude = Gdx.audio.newSound(Gdx.files.internal("errou_rude.ogg"));
        som_lancar = Gdx.audio.newSound(Gdx.files.internal("whip.ogg"));
        som_vidro = Gdx.audio.newSound(Gdx.files.internal("glass.ogg"));
        som_lixo= Gdx.audio.newSound(Gdx.files.internal("trash.ogg"));
        som_soco= Gdx.audio.newSound(Gdx.files.internal("punch.ogg"));
        sprites = carregarTextura("sprites.png");
        lixeira_vermelha = new TextureRegion(sprites, 0, 0, 80, 100);
        lixeira_verde = new TextureRegion(sprites, 80, 0, 80, 100);
        lixeira_amarela = new TextureRegion(sprites, 160, 0, 80, 100);
        lixeira_azul = new TextureRegion(sprites, 240, 0, 80, 100);
        estilingue_tras = new TextureRegion(sprites, 0, 100, 100, 130);
        estilingue_frente = new TextureRegion(sprites, 100, 100, 48, 57);
        vidro_heineken = new TextureRegion(sprites, 180, 100, 32, 48);
        plastico_shake = new TextureRegion(sprites, 212, 100, 32, 48);
        metal_iphone = new TextureRegion(sprites, 244, 100, 32, 48);
        papel_aps = new TextureRegion(sprites, 276, 100, 32, 48);
        txt_iniciar = new TextureRegion(sprites, 320, 0, 120, 40);
        txt_ajuda = new TextureRegion(sprites, 320, 40, 120, 40);
        txt_voltar = new TextureRegion(sprites, 320, 80, 120, 40);
        titulo = new TextureRegion(sprites, 100, 157, 128, 70);
        titulo_ajuda = new TextureRegion(sprites, 228, 170, 152, 64);
        sombra = new TextureRegion(sprites, 380, 170, 60, 51);
        musica_ligada = new TextureRegion(sprites, 148, 116, 16, 16);
        musica_desligada = new TextureRegion(sprites, 164, 116, 16, 16);
        som_ligado = new TextureRegion(sprites, 148, 100, 16, 16);
        som_desligado = new TextureRegion(sprites, 164, 100, 16, 16);
        btn_sair = new TextureRegion(sprites, 148, 132, 16, 16);
        TextureRegion[] frames = new TextureRegion(new Texture("grass.png")).split(80, 120)[0];
        grama = new Animation(0.33f, 
                frames[0],  frames[1],  frames[2],  frames[3],  frames[4], 
                frames[5],  frames[6],  frames[7],  frames[8],  frames[9], 
                frames[10], frames[11], frames[12], frames[13], frames[14], 
                frames[15], frames[16], frames[17], frames[18], frames[19], 
                frames[20], frames[21], frames[22], frames[23] ,frames[24]); 
    }
    
    static Texture carregarTextura(String textura)
    {
        return new Texture(Gdx.files.internal(textura));
    }

    public static void tocarSom(Sound som)
    {
        if(tocar_sons) som.play();
    }
    
    public static TextureRegion getMusicaTextureRegion()
    {
        if(tocar_musica)
            return musica_ligada;
        else
            return musica_desligada;
    }
    
    public static TextureRegion getSomTextureRegion()
    {
        if (tocar_sons)
            return som_ligado;
        else
            return som_desligado;
    }

    // odeio nomes gigantes. não sou javeiro então foda-se
    // btw camel-case é a salvação
    public static void alternarMusicaOnOff()
    {
        tocar_musica = !tocar_musica;
        if (musica.isPlaying())
            musica.pause();
        else
            musica.play();
    }
    public static void alternarSomOnOff()
    {
        tocar_sons = !tocar_sons;
        if (!tocar_sons)
        {
            //somente os sons mais longos
            som_rude.stop();
            som_faustao.stop();
            som_vidro.stop();
        }
    }
}
