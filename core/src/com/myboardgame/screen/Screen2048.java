package com.myboardgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myboardgame.MyGdxGame;
import com.myboardgame.debug.DebugCameraController;
import com.myboardgame.util.Assets;
import com.myboardgame.util.GdxUtils;

public class Screen2048 extends ScreenAdapter{
    public static final Logger log = new Logger(Screen2048.class.getName(), Logger.DEBUG);
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public OrthographicCamera cameraFont;
    public Viewport viewportFont;

    DebugCameraController dcc;
    ShapeRenderer sr;
    GestureDetector gestureDetector;

    MyGdxGame gameMain;

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

    public Screen2048(MyGdxGame game) {
        this.gameMain = game;
        camera = new OrthographicCamera();
        cameraFont = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewportFont = new ScreenViewport(cameraFont);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        board = new BoardArray(4, 4);
        boardCam = new OrthographicCamera(board.width, board.height ); // + HUD_H
        boardV = new FitViewport(board.width, board.height, boardCam); // + HUD HEIGTH
        boardV.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        init();
        boardCam.update();

        //hud = new HUD(0, (float) (minefieldBoard.height + HUD_H), (float) minefieldBoard.width, HUD_H, this); //??
        Vector3 tmp = new Vector3(1, 0, 0);
        GdxUtils.ProjectWorldCoordinatesInScreenCoordinates(boardCam, tmp);
        /*gestureDetector = new GestureDetector(tmp.x / 2.5f, 0.2f, 0.5f, 0.5f, this);
        Gdx.input.setInputProcessor(gestureDetector);
        listeners = new Array<MyClickListener>();
        listeners.add(hud);*/
    }

    @Override
    public void show() {
        super.show();
        dcc = new DebugCameraController();
        dcc.setStartPosition(board.width / 2, (board.height) / 2); // + HUD HEIGHT
    }

    private void init() {
        state = GameState.BEGIN;
        boardCam.update();
    }

    public void inputHandle() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) gameMain.safeExit();
    }

    @Override
    public void render(float delta) {
        //update(); //Also state HUD UPDATE
        dcc.handleDebugInput(delta);
        dcc.applyTo(boardCam);
        boardCam.update();
        GdxUtils.clearScreen(Color.LIGHT_GRAY);
        batch.begin();
        batch.setProjectionMatrix(boardCam.combined);
        /*drawHUD(delta);
        hud.renderUpdate(batch, 0);*/
        drawBoard();
        drawBoardNet4Debug();
        batch.end();
        inputHandle();
    }

    private void drawBoardNet4Debug() {
        sr.setColor(Color.LIGHT_GRAY);
        sr.setProjectionMatrix(boardCam.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        {
            sr.rect(0, 0, board.width, board.height); // +HUD
        }
        sr.end();

        sr.setProjectionMatrix(boardCam.combined);
        sr.setColor(Color.DARK_GRAY);
        sr.begin(ShapeRenderer.ShapeType.Line);
        {
            for (int x = 0; x <= board.width; x++) { //vertical lines
                sr.line(x, 0, x, board.height);
            }

            for (int y = 0; y <= board.height; y++) { //horizontal lines
                sr.line(0, y, board.width, y);
            }
        }
        sr.end();
    }

    private void drawBoard() {
        batch.setProjectionMatrix(boardCam.combined);
        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_2)){
                    //NARISI CELL 2;
                    batch.draw(Assets.cell2, x, y, 1, 1,0,0, Assets.cell2.getWidth(), Assets.cell2.getHeight(), false, false);
                    continue;
                }
                if(board.boardArray[x][y].contains(BoardArray.CellState.CELL_4)){
                    //NARISI CELL 4;
                    batch.draw(Assets.cell4, x, y ,1, 1,0,0, Assets.cell2.getWidth(), Assets.cell2.getHeight(), false, false);
                    continue;
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewportFont.update(width, height, true);
        boardV.update(width, height, true);
        //hud.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();

        sr.dispose();
        log.debug("Dispose Screen");
    }
}
