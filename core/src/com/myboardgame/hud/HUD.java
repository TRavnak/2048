package com.myboardgame.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.myboardgame.screen.Screen2048;
import com.myboardgame.util.GdxUtils;

public class HUD implements Disposable {
    Screen2048 game;
    GameObjectScore score;
    BitmapFont font;
    float x;
    float y;
    float w;
    float h;

    public HUD(float x, float y, float w, float h, Screen2048 game) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.game = game;
        createFontBasedObjects();
    }

    public void updateScore(int add){
        score.addScore(add);
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    private void createFontBasedObjects() {
        font = GdxUtils.getTTFFontInWorldUnits(0.4f, game.boardV.getWorldHeight());
        Vector3 tmpInWordCoordinateToScreen = new Vector3();
        tmpInWordCoordinateToScreen.set(x, y - h / 2 + 0.5f, 0);
        GdxUtils.ProjectWorldCoordinatesInScreenCoordinates(game.boardCam, tmpInWordCoordinateToScreen);
        score = new GameObjectScore(tmpInWordCoordinateToScreen.x, tmpInWordCoordinateToScreen.y, 3, h, font);

    }

    public void renderUpdate(SpriteBatch batch, float dt){
        batch.setProjectionMatrix(game.cameraFont.combined);
        score.render(batch);
    }

    public void resize(int width, int height) {
        if (score != null) score.dispose();
        if (font != null) font.dispose();
        createFontBasedObjects();
    }
}
