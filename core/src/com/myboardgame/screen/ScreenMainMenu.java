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
import com.myboardgame.components.MyDialog;
import com.myboardgame.util.GdxUtils;

public class ScreenMainMenu extends ScreenAdapter {
    private static float SMALL_PAD = 8;
    private static float BIG_PAD = 30;
    private MyGdxGame game;
    private AssetManager am;

    private TextureRegion back;
    private SpriteBatch batch;
    private final FitViewport viewport;
    private Skin uiskin;

    private Table tableMain; //https://github.com/libgdx/libgdx/wiki/Table
    private TextButton btnNewGame;
    private TextButton btnResult;
    private TextButton btnExit;
    private Label lblHead;
    private Label lblName;
    private TextField textFieldName;
    private Label warning;
    private Stage stage;
    private MyDialog endDialog;


    public ScreenMainMenu(MyGdxGame myGame) {
        this.game = myGame;
        am = game.getAssetManager();
        uiskin = am.get(AssetDescriptors.DEFAULT_SKIN);
        TextureAtlas gamePlayAtlas = am.get(AssetDescriptors.MY_ATLAS);
        back = gamePlayAtlas.findRegion(RegionNames.BACK);
        batch = game.getBatch();
        viewport = new FitViewport(300, 400);
        initDialog();
        initGUI();
    }

    private void initDialog() {
        endDialog = new MyDialog("Save this name ", uiskin, "dialog") {
            protected void result(Object object) {
                switch ((DialogSelected) object) {
                    case yes:
                        System.out.println("Save");
                        break;
                    case cancel:
                        System.out.println("Cancel");
                        break;
                }
            };
        };
        endDialog.addButton("Save", MyDialog.DialogSelected.yes, uiskin.get("default", TextButton.TextButtonStyle.class)).size(100).expand().left();
        endDialog.addButton("Cancel", MyDialog.DialogSelected.cancel, uiskin.get("default", TextButton.TextButtonStyle.class)).size(100).expand().right();

    }

    private void initGUI() {
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        tableMain = new Table(uiskin);

        lblHead = new Label("2048", uiskin);
        tableMain.add(lblHead).expandX().pad(BIG_PAD).row();

        warning = new Label("", uiskin);
        warning.setAlignment(Align.center);
        warning.setColor(Color.RED);
        tableMain.add(warning).fillX().pad(SMALL_PAD).row();

        Table nameTable = new Table();
        Label addressLabel = new Label("Name: ", uiskin);
        textFieldName = new TextField("", uiskin);
        nameTable.add(addressLabel);
        nameTable.add(textFieldName).width(200);
        nameTable.row();
        tableMain.add(nameTable).fillX().pad(SMALL_PAD).row();

        btnNewGame = new TextButton("NEW GAME", uiskin);
        btnNewGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(textFieldName.getText().length() < 1){
                    warning.setText("BEFORE START INPUT NAME");
                }
                else {
                    super.clicked(event, x, y);
                    GameManager.INSTANCE.userID = textFieldName.getText();
                    game.goToScreen(MyGdxGame.Screen.GAME);
                }
            }
        });
        tableMain.add(btnNewGame).fillX().pad(SMALL_PAD).row();

        btnResult = new TextButton("RESULT", uiskin);
        btnResult.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.goToScreen(MyGdxGame.Screen.RESULT);
            }
        });
        tableMain.add(btnResult).fillX().pad(SMALL_PAD).row();

        btnExit = new TextButton("EXIT", uiskin);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.exit();
            }
        });
        tableMain.add(btnExit).fillX().pad(SMALL_PAD).padTop(BIG_PAD).padBottom(BIG_PAD).row();

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
