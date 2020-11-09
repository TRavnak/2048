package com.myboardgame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture emptyCell;
    public static Texture cell2;
    public static Texture cell4;
    public static Texture cell8;
    public static Texture cell16;
    public static Texture cell32;
    public static Texture cell64;
    public static Texture cell128;
    public static Texture cell256;
    public static Texture cell512;
    public static Texture cell1024;
    public static Texture cell2048;

    public static void Load() {
        cell2 = new Texture(Gdx.files.internal(Constants.CELL2));
        cell4 = new Texture(Gdx.files.internal(Constants.CELL4));
        cell8 = new Texture(Gdx.files.internal(Constants.CELL8));
        cell16 = new Texture(Gdx.files.internal(Constants.CELL16));
        cell32 = new Texture(Gdx.files.internal(Constants.CELL32));
        cell64 = new Texture(Gdx.files.internal(Constants.CELL64));
        cell128 = new Texture(Gdx.files.internal(Constants.CELL128));
        cell256 = new Texture(Gdx.files.internal(Constants.CELL256));
        cell512 = new Texture(Gdx.files.internal(Constants.CELL512));
        cell1024 = new Texture(Gdx.files.internal(Constants.CELL1024));
        cell2048 = new Texture(Gdx.files.internal(Constants.CELL2048));
    }

    public static void Dispose(){
        cell2.dispose();
        cell4.dispose();
        cell8.dispose();
        cell16.dispose();
        cell32.dispose();
        cell64.dispose();
        cell128.dispose();
        cell256.dispose();
        cell512.dispose();
        cell1024.dispose();
        cell2048.dispose();
    }
}
