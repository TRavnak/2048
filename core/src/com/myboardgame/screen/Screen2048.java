package com.myboardgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myboardgame.MyGdxGame;
import com.myboardgame.assets.AssetDescriptors;
import com.myboardgame.assets.AssetPaths;
import com.myboardgame.assets.RegionNames;
import com.myboardgame.debug.DebugCameraController;
import com.myboardgame.hud.HUD;
import com.myboardgame.util.GdxUtils;

public class Screen2048 extends ScreenAdapter{
    public static final Logger log = new Logger(Screen2048.class.getName(), Logger.DEBUG);
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public OrthographicCamera cameraFont;
    public Viewport viewportFont;
    private TextureAtlas gamePlayAtlas;
    private AssetManager am;

    DebugCameraController dcc;
    ShapeRenderer sr;
    GestureDetector gestureDetector;

    MyGdxGame gameMain;
    private Animation<TextureRegion> winAnimation;
    float stateTime;

    public BoardArray board;
    public OrthographicCamera boardCam;
    public Viewport boardV;

    public enum GameState {
        BEGIN, //blank board clock is not running
        RUNNING, //clock starts to run
        END_WIN, //end
        END_LOSE
    }

    public GameState state;
    HUD hud;
    public static final int HUD_H = 2;

    public Screen2048(MyGdxGame game) {
        this.gameMain = game;
        camera = new OrthographicCamera();
        cameraFont = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewportFont = new ScreenViewport(cameraFont);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        board = new BoardArray(4, 4);
        boardCam = new OrthographicCamera(board.width, board.height + HUD_H );
        boardV = new FitViewport(board.width, board.height + HUD_H, boardCam);
        boardV.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        am = game.getAssetManager();
        gamePlayAtlas = am.get(AssetDescriptors.MY_ATLAS);

        init();
        boardCam.update();

        winAnimation = new Animation(0.1f, gamePlayAtlas.findRegions(RegionNames.IMG_2048), Animation.PlayMode.LOOP);
        stateTime = 0f;
        state = GameState.RUNNING;
        hud = new HUD(0, (float) (board.height + HUD_H - 0), (float) board.width, HUD_H, this);
    }

    @Override
    public void show() {
        super.show();
        dcc = new DebugCameraController();
        dcc.setStartPosition(board.width / 2, (board.height + HUD_H) / 2 ); // + HUD HEIGHT
    }

    private void init() {
        state = GameState.BEGIN;
        boardCam.update();
    }

    public void inputHandle() {
        if(state == GameState.RUNNING) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) gameMain.safeExit();
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) commandUp();
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) commandDown();
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) commandRight();
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) commandLeft();
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) board.cheat();
        }
    }

    private void drawHUD(float delta) {
        hud.renderUpdate(batch, delta);
    }

    @Override
    public void render(float delta) {
        dcc.handleDebugInput(delta);
        dcc.applyTo(boardCam);
        boardCam.update();
        GdxUtils.clearScreen(Color.LIGHT_GRAY);
        batch.begin();
        batch.setProjectionMatrix(boardCam.combined);
        drawHUD(delta);
        hud.renderUpdate(batch, 0);
        drawBoard();
        batch.end();
        stateTime+=delta;
        inputHandle();
        if(board.end)state = GameState.END_WIN;
    }


    private void drawBoard() {
        batch.setProjectionMatrix(boardCam.combined);
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_2)){
                    //NARISI CELL 2;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_2), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_4)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_4), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_8)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_8), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_16)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_16), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_32)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_32), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_64)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_64), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_128)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_128), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_256)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_256), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_512)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_512), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_1024)){
                    //NARISI CELL 4;
                    batch.draw(gamePlayAtlas.findRegion(RegionNames.IMG_1024), x, y+0.5f, 1, 1);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_2048)){
                    //NARISI CELL 4;
                    batch.draw(winAnimation.getKeyFrame(stateTime), x, y+0.5f, 1, 1);
                    continue;
                }
            }
        }
    }

    public void commandUp(){
        boolean moved = moveUp();
        boolean merged = mergeUp();
        if(merged) moveUp();
        if(merged || moved) board.addNew();
    }

    public boolean moveUp(){
        boolean moved = false;
        for (int y = board.height -1; y >= 0; y--) {
            for (int x = 0; x < board.width; x++) {
                for(int i = 0; i < board.height; i++){
                    if(i+y+1 > board.height - 1) break;
                    if(board.boardArray[x][y+1+i].contains(BoardArray.CellState.CELL_EMPTY) && !board.boardArray[x][y+i].contains(BoardArray.CellState.CELL_EMPTY)){
                        moved = true;
                        board.boardArray[x][y+1+i].clear();
                        board.boardArray[x][y+1+i].add(board.getCellState(x, y+i));
                        board.boardArray[x][y+i].clear();
                        board.boardArray[x][y+i].add(BoardArray.CellState.CELL_EMPTY);
                    }
                }
            }
        }
        return moved;
    }

    public boolean mergeUp(){
        boolean merge = false;
        for (int y = board.height -2; y >= 0; y--) {
            for (int x = 0; x < board.width; x++) {
                if(board.boardArray[x][y+1].toString().equals(board.boardArray[x][y].toString()) && !board.boardArray[x][y].contains(BoardArray.CellState.CELL_EMPTY)){
                    merge = true;
                    board.boardArray[x][y+1].clear();
                    board.boardArray[x][y+1].add(board.getMergedCell(x, y));
                    updateScore(x, y+1);
                    board.boardArray[x][y].clear();
                    board.boardArray[x][y].add(BoardArray.CellState.CELL_EMPTY);
                }

            }
        }
        return merge;
    }

    public void commandDown(){
        boolean moved = moveDown();
        boolean merged = mergeDown();
        if(merged) moveDown();
        if(merged || moved) board.addNew();
    }

    public boolean moveDown(){
        boolean moved = false;
        for (int y = 0; y < board.height; y++) {
            for (int x = 0; x < board.width; x++) {
                for(int i = 0; i < board.height; i++){
                    if(y-i-1 < 0) break;
                    if(board.boardArray[x][y-i-1].contains(BoardArray.CellState.CELL_EMPTY) && !board.boardArray[x][y-i].contains(BoardArray.CellState.CELL_EMPTY)){
                        moved = true;
                        board.boardArray[x][y-i-1].clear();
                        board.boardArray[x][y-i-1].add(board.getCellState(x, y-i));
                        board.boardArray[x][y-i].clear();
                        board.boardArray[x][y-i].add(BoardArray.CellState.CELL_EMPTY);
                    }
                }
            }
        }
        return moved;
    }

    public boolean mergeDown(){
        boolean merge = false;
        for (int y = 1; y < board.height; y++) {
            for (int x = 0; x < board.width; x++) {
                if(board.boardArray[x][y-1].toString().equals(board.boardArray[x][y].toString()) && !board.boardArray[x][y].contains(BoardArray.CellState.CELL_EMPTY)){
                    merge = true;
                    board.boardArray[x][y-1].clear();
                    board.boardArray[x][y-1].add(board.getMergedCell(x, y));
                    updateScore(x, y-1);
                    board.boardArray[x][y].clear();
                    board.boardArray[x][y].add(BoardArray.CellState.CELL_EMPTY);
                }

            }
        }
        return merge;
    }

    public void commandRight(){
        boolean moved = moveRight();
        boolean merged = mergeRight();
        if(merged) moveRight();
        if(merged || moved) board.addNew();
    }

    public boolean moveRight(){
        boolean moved = false;
        for (int y = 0; y < board.height; y++) {
            for (int x = board.width - 1; x >=0; x--) {
                for(int i = 0; i < board.width; i++){
                    if(x+i+1 > board.width - 1) break;
                    if(board.boardArray[x+i+1][y].contains(BoardArray.CellState.CELL_EMPTY) && !board.boardArray[x+i][y].contains(BoardArray.CellState.CELL_EMPTY)){
                        moved = true;
                        board.boardArray[x+i+1][y].clear();
                        board.boardArray[x+i+1][y].add(board.getCellState(x+i, y));
                        board.boardArray[x+i][y].clear();
                        board.boardArray[x+i][y].add(BoardArray.CellState.CELL_EMPTY);
                    }
                }
            }
        }
        return moved;
    }

    public boolean mergeRight(){
        boolean merge = false;
        for (int y = 0; y < board.height; y++) {
            for (int x = board.width - 2; x >=0; x--) {
                if(board.boardArray[x+1][y].toString().equals(board.boardArray[x][y].toString()) && !board.boardArray[x][y].contains(BoardArray.CellState.CELL_EMPTY)){
                    merge = true;
                    board.boardArray[x+1][y].clear();
                    board.boardArray[x+1][y].add(board.getMergedCell(x, y));
                    updateScore(x+1, y);
                    board.boardArray[x][y].clear();
                    board.boardArray[x][y].add(BoardArray.CellState.CELL_EMPTY);
                }

            }
        }
        return merge;
    }

    public void commandLeft(){
        boolean moved = moveLeft();
        boolean merged = mergeLeft();
        if(merged) moveLeft();
        if(merged || moved) board.addNew();
    }

    public boolean moveLeft() {
        boolean moved = false;
        for (int y = 0; y < board.height; y++) {
            for (int x = 0; x < board.width; x++) {
                for (int i = 0; i < board.width; i++) {
                    if (x - i - 1 < 0) break;
                    if (board.boardArray[x - i - 1][y].contains(BoardArray.CellState.CELL_EMPTY) && !board.boardArray[x - i][y].contains(BoardArray.CellState.CELL_EMPTY)) {
                        moved = true;
                        board.boardArray[x - i - 1][y].clear();
                        board.boardArray[x - i - 1][y].add(board.getCellState(x - i, y));
                        board.boardArray[x - i][y].clear();
                        board.boardArray[x - i][y].add(BoardArray.CellState.CELL_EMPTY);
                    }
                }
            }
        }
        return moved;
    }

    public boolean mergeLeft(){
        boolean merge = false;
        for (int y = 0; y < board.height; y++) {
            for (int x = 1; x < board.width; x++) {
                if(board.boardArray[x-1][y].toString().equals(board.boardArray[x][y].toString()) && !board.boardArray[x][y].contains(BoardArray.CellState.CELL_EMPTY)){
                    merge = true;
                    board.boardArray[x-1][y].clear();
                    board.boardArray[x-1][y].add(board.getMergedCell(x, y));
                    updateScore(x-1, y);
                    board.boardArray[x][y].clear();
                    board.boardArray[x][y].add(BoardArray.CellState.CELL_EMPTY);
                }

            }
        }
        return merge;
    }

    public void updateScore(int x, int y) {
        switch (board.boardArray[x][y].toString()) {
            case "[CELL_4]":
                hud.updateScore(4);
                break;
            case "[CELL_8]":
                hud.updateScore(8);
                break;
            case "[CELL_16]":
                hud.updateScore(16);
                break;
            case "[CELL_32]":
                hud.updateScore(32);
                break;
            case "[CELL_64]":
                hud.updateScore(64);
                break;
            case "[CELL_128]":
                hud.updateScore(128);
                break;
            case "[CELL_256]":
                hud.updateScore(256);
                break;
            case "[CELL_512]":
                hud.updateScore(512);
                break;
            case "[CELL_1024]":
                hud.updateScore(1024);
                break;
            case "[CELL_2048]":
                hud.updateScore(2048);
                break;
        }
        am.get(AssetDescriptors.MERGE_SOUND).play();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewportFont.update(width, height, true);
        boardV.update(width, height, true);
        hud.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();

        sr.dispose();
        log.debug("Dispose Screen");
    }
}
