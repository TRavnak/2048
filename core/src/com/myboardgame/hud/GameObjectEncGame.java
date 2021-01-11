package com.myboardgame.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObjectEncGame extends GameObject {
    BitmapFont font; //reference

    public GameObjectEncGame(float x, float y, float width, float height, BitmapFont font) {
        super(x, y, width, height);
        this.font = font;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.setColor(Color.RED);
        font.draw(batch, "GAME OVER", bounds.x, bounds.y);
    }

    @Override
    public void dispose() {}
}
