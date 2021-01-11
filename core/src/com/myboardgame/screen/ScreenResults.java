package com.myboardgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.myboardgame.GameManager;
import com.myboardgame.MyGdxGame;
import com.myboardgame.assets.AssetDescriptors;
import com.myboardgame.assets.RegionNames;
import com.myboardgame.util.GdxUtils;

public class ScreenResults extends ScreenAdapter {
    private static float SMALL_PAD = 8;
    private static float BIG_PAD = 30;
    private MyGdxGame game;
    private AssetManager am;

    private TextureRegion back;
    private SpriteBatch batch;
    private final FitViewport viewport;
    private Skin uiskin;

    private Table tableMain;
    private Label lblHead;
    private Stage stage;


    public ScreenResults(MyGdxGame myGame) {
        System.out.println("NEW SCREEN");
        this.game = myGame;
        am = game.getAssetManager();
        uiskin = am.get(AssetDescriptors.DEFAULT_SKIN);
        TextureAtlas gamePlayAtlas = am.get(AssetDescriptors.MY_ATLAS);
        back = gamePlayAtlas.findRegion(RegionNames.BACK);
        batch = game.getBatch();
        viewport = new FitViewport(300, 400);
        initGUI();
    }

    private void initGUI() {
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        tableMain = new Table(uiskin);

        lblHead = new Label("RESULTS", uiskin);
        tableMain.add(lblHead).expandX().pad(BIG_PAD).row();

        for(int i = 0; i < 10 && i < GameManager.INSTANCE.results.list.size(); i++){
            Label pos = new Label(""+GameManager.INSTANCE.results.list.get(i).position, uiskin);
            Label name = new Label(""+GameManager.INSTANCE.results.list.get(i).name, uiskin);
            Label score = new Label(""+GameManager.INSTANCE.results.list.get(i).score, uiskin);
            Table tmp = new Table();
            tmp.add(pos).width(50);
            tmp.add(name).width(100);
            tmp.add(score).width(50);
            tableMain.add(tmp).row();
        }

        Button btnBack = new TextButton("BACK", uiskin);
        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.goToScreen(MyGdxGame.Screen.MAIN);
            }
        });
        tableMain.add(btnBack).fillX().pad(SMALL_PAD).padTop(BIG_PAD).padBottom(BIG_PAD).row();

        tableMain.setFillParent(true);

        tableMain.add(new Container()).expandY();
        tableMain.pack();
        // stage.addActor(intro);
        stage.addActor(tableMain);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        myRender(delta);
    }

    public void myRender(float delta) {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        GdxUtils.clearScreen();
        game.getBatch().draw(back, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        tableMain.pack();
    }
}
