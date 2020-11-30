package com.myboardgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Logger;
import com.myboardgame.assets.AssetDescriptors;
import com.myboardgame.assets.AssetPaths;
import com.myboardgame.screen.Screen2048;
import com.myboardgame.util.GdxUtils;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	GameManager gameManager;
	AssetManager assetManager;

	public void load() {
		assetManager.load(AssetDescriptors.MY_ATLAS);
		assetManager.load(AssetDescriptors.MERGE_SOUND);
		assetManager.finishLoading();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		gameManager = new GameManager();
		load();
		TextureAtlas atlas = new TextureAtlas(AssetPaths.MY_ATLAS);
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
		batch.dispose();
	}
}
