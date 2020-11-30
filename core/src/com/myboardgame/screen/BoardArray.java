package com.myboardgame.screen;

import com.badlogic.gdx.math.MathUtils;

import java.util.EnumSet;

public class BoardArray {
    public int width;
    public int height;
    public boolean end;

    public enum CellState {
        CELL_EMPTY, CELL_2, CELL_4, CELL_8, CELL_16, CELL_32, CELL_64, CELL_128, CELL_256,
        CELL_512, CELL_1024, CELL_2048;

        public static EnumSet<CellState> ALL_OPTS = EnumSet.allOf(CellState.class);
    }

    public EnumSet<CellState>[][] boardArray; // 2-dimensional array

    public BoardArray(int width, int height) {
        this.end = false;
        this.height = height;
        this.width = width;
        boardArray = (EnumSet<CellState>[][]) new EnumSet<?>[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardArray[x][y] = EnumSet.allOf(CellState.class);
            }
        }
        clearAndInit();
    }

    public void clearAndInit() {
        clearBoardArray();
        initializeBoard();
        addNew();
        addNew();
    }

    public void izpis() {
        for (int y = height -1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                System.out.print(boardArray[x][y] + " | ");
                //System.out.print(x + " " + y + " " + boardArray[x][y] + " | ");
            }
            System.out.println();
        }
    }

    public void clearBoardArray() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardArray[x][y].clear(); //ADD
            }
        }
    }

    public void initializeBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardArray[x][y].add(CellState.CELL_EMPTY); //ADD
            }
        }
    }

    public void addNew() {
        if(end) return;
        while (true) {
            int x = MathUtils.random(0, width - 1);
            int y = MathUtils.random(0, height - 1);
            if (boardArray[x][y].contains(CellState.CELL_EMPTY)) {
                if (MathUtils.random(0, 10) < 0) {
                    boardArray[x][y].clear();
                    boardArray[x][y].add(CellState.CELL_4);
                } else {
                    boardArray[x][y].clear();
                    boardArray[x][y].add(CellState.CELL_2);
                }
                break;
            }
        }
    }

    public void cheat() {
        if(end) return;
        end = true;
        while (true) {
            int x = MathUtils.random(0, width - 1);
            int y = MathUtils.random(0, height - 1);
            if (boardArray[x][y].contains(CellState.CELL_EMPTY)) {
                boardArray[x][y].clear();
                boardArray[x][y].add(CellState.CELL_2048);
                break;
            }
        }
    }




    public CellState getCellState(int x, int y){
        switch (boardArray[x][y].toString()){
            case "[CELL_2]" : return CellState.CELL_2;
            case "[CELL_4]" : return CellState.CELL_4;
            case "[CELL_8]" : return CellState.CELL_8;
            case "[CELL_16]" : return CellState.CELL_16;
            case "[CELL_32]" : return CellState.CELL_32;
            case "[CELL_64]" : return CellState.CELL_64;
            case "[CELL_128]" : return CellState.CELL_128;
            case "[CELL_256]" : return CellState.CELL_256;
            case "[CELL_512]" : return CellState.CELL_512;
            case "[CELL_1024]" : return CellState.CELL_1024;
            case "[CELL_2048]" : return CellState.CELL_2048;
        }
        return CellState.CELL_EMPTY;
    }

    public CellState getMergedCell(int x, int y){
        switch (boardArray[x][y].toString()){
            case "[CELL_2]" : return CellState.CELL_4;
            case "[CELL_4]" : return CellState.CELL_8;
            case "[CELL_8]" : return CellState.CELL_16;
            case "[CELL_16]" : return CellState.CELL_32;
            case "[CELL_32]" : return CellState.CELL_64;
            case "[CELL_64]" : return CellState.CELL_128;
            case "[CELL_128]" : return CellState.CELL_256;
            case "[CELL_256]" : return CellState.CELL_512;
            case "[CELL_512]" : return CellState.CELL_1024;
            case "[CELL_1024]" : return CellState.CELL_2048;
        }
        return CellState.CELL_EMPTY;
    }


    public void endGame(){
        boolean test = true;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(boardArray[i][j].contains(CellState.CELL_EMPTY)) test = false;
            }
        }
        end = test;
    }
}
