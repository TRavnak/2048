package com.myboardgame.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.myboardgame.GameManager;
import com.myboardgame.screen.Screen2048;
import com.myboardgame.util.GdxUtils;

public class HUD implements Disposable {
    Screen2048 game;
    GameObjectScore score;
    GameObjectHighScore bScore;
    GameObjectName name;
    GameObjectEncGame endMsg;
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

    public int getScore(){
        return score.score;
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    private void createFontBasedObjects() {
        font = GdxUtils.getTTFFontInWorldUnits(0.35f, game.boardV.getWorldHeight());

        Vector3 tmpInWordCoordinateToScreen = new Vector3();

        tmpInWordCoordinateToScreen.set(x+0.1f, y - h / 2 + 0.4f, 0);
        GdxUtils.ProjectWorldCoordinatesInScreenCoordinates(game.boardCam, tmpInWordCoordinateToScreen);
        score = new GameObjectScore(tmpInWordCoordinateToScreen.x, tmpInWordCoordinateToScreen.y, 3, 0.5f, font);

        tmpInWordCoordinateToScreen.set(x + w / 2 - 0.2f, y-0.1f, 0);
        GdxUtils.ProjectWorldCoordinatesInScreenCoordinates(game.boardCam, tmpInWordCoordinateToScreen);
        name = new GameObjectName(tmpInWordCoordinateToScreen.x, tmpInWordCoordinateToScreen.y, 3, 0.5f, font, GameManager.INSTANCE.userID);

        tmpInWordCoordinateToScreen.set(x + w / 2 + 0.5f, y - h / 2 + 0.4f, 0);
        GdxUtils.ProjectWorldCoordinatesInScreenCoordinates(game.boardCam, tmpInWordCoordinateToScreen);
        bScore = new GameObjectHighScore(tmpInWordCoordinateToScreen.x, tmpInWordCoordinateToScreen.y, 3, 0.5f, font);

        tmpInWordCoordinateToScreen.set(x + w / 2 - 0.6f, y - h / 2, 0);
        GdxUtils.ProjectWorldCoordinatesInScreenCoordinates(game.boardCam, tmpInWordCoordinateToScreen);
        endMsg = new GameObjectEncGame(tmpInWordCoordinateToScreen.x, tmpInWordCoordinateToScreen.y, 3, 0.5f, font);

    }

    public void renderUpdate(SpriteBatch batch, float dt){
        batch.setProjectionMatrix(game.cameraFont.combined);
        score.render(batch);
        bScore.render(batch);
        name.render(batch);
        if(GameManager.INSTANCE.isGameOver) {
            endMsg.render(batch);
        }
    }


    public void resize(int width, int height) {
        if (score != null) score.dispose();
        if (name != null) name.dispose();
        if (font != null) font.dispose();
        createFontBasedObjects();
    }
}
