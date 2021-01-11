package com.myboardgame.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myboardgame.GameManager;

public class GameObjectHighScore  extends GameObject {
    int score;
    BitmapFont font; //reference

    public GameObjectHighScore(float x, float y, float width, float height, BitmapFont font) {
        super(x, y, width, height);
        this.font = font;
    }

    public void addScore(int add) {
        score+=add;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.setColor(Color.RED);
        font.draw(batch, "BEST: "+ GameManager.INSTANCE.results.list.get(0).score, bounds.x, bounds.y);
    }

    @Override
    public void dispose() {}
}
