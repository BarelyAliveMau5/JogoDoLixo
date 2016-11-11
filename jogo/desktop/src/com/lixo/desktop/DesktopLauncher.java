package com.lixo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lixo.Assets;
import com.lixo.Lixo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Assets.TELA_LARGURA;
		config.height = Assets.TELA_ALTURA;
		new LwjglApplication(new Lixo(), config);
	}
}
