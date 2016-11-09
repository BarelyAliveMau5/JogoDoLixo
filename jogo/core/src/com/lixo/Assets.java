package com.lixo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets
{
	public static Texture gramado;
	public static Texture carregarTextura(String textura)
	{
		return new Texture(Gdx.files.internal(textura));
	}
	
	public static void carregarTudo()
	{
		gramado = carregarTextura("gramado.png");
	}
}
