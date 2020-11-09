package com.myboardgame.hud;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class GameObject implements Disposable {
    public final Vector2 position;
    public final Rectangle bounds;

    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, width, height);
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }

    public float getW() {
        return bounds.width;
    }

    public float getH() {
        return bounds.height;
    }

    public float getOrgX() {
        return bounds.width / 2;
    }

    public float getOrgY() {
        return bounds.height / 2;
    }

    public abstract void render(SpriteBatch batch);

}
