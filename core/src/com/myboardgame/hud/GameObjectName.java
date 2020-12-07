package com.myboardgame.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObjectName extends GameObject{
    String name;
    BitmapFont font; //reference

    public GameObjectName(float x, float y, float width, float height, BitmapFont font, String name) {
        super(x, y, width, height);
        this.name = name;
        this.font = font;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.setColor(Color.RED);
        font.draw(batch, name, bounds.x, bounds.y);
    }

    @Override
    public void dispose() {}
}
