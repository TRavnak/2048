package com.myboardgame.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myboardgame.util.GdxUtils;

public class GameObjectScore extends GameObject {
    int score;
    BitmapFont font; //reference

    public GameObjectScore(float x, float y, float width, float height, BitmapFont font) {
        super(x, y, width, height);
        score = 0;
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println(width);
        System.out.println(height);
        this.font = font;
    }

    public void addScore(int add) {
        score+=add;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.setColor(Color.RED);
        font.draw(batch, "SCORE: "+score, bounds.x, bounds.y);
    }

    @Override
    public void dispose() {}
}
