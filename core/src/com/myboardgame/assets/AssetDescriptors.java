package com.myboardgame.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetDescriptors {

    public static final AssetDescriptor<TextureAtlas> MY_ATLAS =
            new AssetDescriptor<TextureAtlas>(AssetPaths.MY_ATLAS, TextureAtlas.class);
    public static final AssetDescriptor<Sound> MERGE_SOUND =
            new AssetDescriptor<Sound>(AssetPaths.MERGE, Sound.class);

    private AssetDescriptors() {}
}
