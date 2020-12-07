package com.myboardgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.myboardgame.assets.AssetDescriptors;
import com.myboardgame.assets.AssetPaths;
import com.myboardgame.screen.ScreenMainMenu;
import com.myboardgame.screen.ScreenResults;
import com.myboardgame.screen.Screen2048;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	GameManager gameManager;
	AssetManager assetManager;

	public void load() {
		assetManager.load(AssetDescriptors.MY_ATLAS);
		assetManager.load(AssetDescriptors.MERGE_SOUND);
		assetManager.load(AssetDescriptors.DEFAULT_SKIN);
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
		gameManager = GameManager.INSTANCE;
		load();
		TextureAtlas atlas = new TextureAtlas(AssetPaths.MY_ATLAS);
		selectFirstScreen();
	}

	public void selectFirstScreen() {
		goToScreen(Screen.MAIN);
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
		assetManager.dispose();
		batch.dispose();
	}



	public static enum Screen {
		MAIN, GAME, RESULT, SETTINGS
	}

	public void exit(){
		GameManager.INSTANCE.saveResults();
		Gdx.app.exit();
	}

	public void goToScreen (Screen screenType){
		switch (screenType) {
			case MAIN:
				System.out.println("GO TO MAIN SCREEN");
				super.setScreen(new ScreenMainMenu(this));
				break;
			case GAME:
				super.setScreen(new Screen2048(this));
				break;
			case RESULT:
				super.setScreen(new ScreenResults(this));
				break;
			default:
		}
		System.gc();
	}
}
