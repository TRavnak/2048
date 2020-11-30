package com.myboardgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.myboardgame.MyGdxGame;



public class DesktopLauncher {
	private static final boolean DRAW_DEBUG_OUTLINE = false;
	private static final String ATLASNAME="myatlas";
	private static final String RAW_ASSETS_PATH = "raw";
	private static final String ASSETS_PATH = "";

	public static void main (String[] arg) {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.debug = DRAW_DEBUG_OUTLINE;

		if (!TexturePacker.processIfModified(settings,
				ATLASNAME+"/"+RAW_ASSETS_PATH,
				ASSETS_PATH + ATLASNAME,
				ATLASNAME
		)) {

		};
		String file=ASSETS_PATH + ATLASNAME+"/"+ATLASNAME+".atlas";

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
	}
}
