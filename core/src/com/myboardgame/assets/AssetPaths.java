package com.myboardgame.assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetPaths {
    public static final String MY_ATLAS = "myatlas/myatlas.atlas";
    public static final String MERGE = "sounds/merge.wav";
    public static String DEFAULT_UISKIN = "skin/default/skin/uiskin.json";
    public static Texture undo;

    private AssetPaths() {
        undo = new Texture(Gdx.files.internal("myatlas/raw/undo.png"));
    }
}
