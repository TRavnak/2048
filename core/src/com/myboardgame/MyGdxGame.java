package com.myboardgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.myboardgame.screen.Screen2048;
import com.myboardgame.util.Assets;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		Gdx.app.setLogLevel(Logger.DEBUG);
		Assets.Load();
		selectFirstScreen();
	}

	public void selectFirstScreen() {
		setScreen(new Screen2048(this));
	}

	@Override
	public void resize(int width, int height) {
		screen.resize( width, height);
	}

	public void safeExit() {
		Gdx.app.exit();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		screen.dispose();
		Assets.Dispose(); //single screen
	}
}
