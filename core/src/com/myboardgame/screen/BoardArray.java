package com.myboardgame.screen;

import com.badlogic.gdx.math.MathUtils;

import java.util.EnumSet;

public class BoardArray {
  public int width;
  public int height;

  public enum CellState {
    CELL_EMPTY, CELL_2, CELL_4, CELL_8, CELL_16, CELL_32, CELL_64, CELL_128, CELL_256,
    CELL_512, CELL_1024, CELL_2048;

    public static EnumSet<CellState> ALL_OPTS = EnumSet.allOf(CellState.class);

  }
  public EnumSet<CellState>[][] boardArray; // 2-dimensional array


  public BoardArray(int width, int height) {
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
    izpis();
  }

  public void izpis(){
    for(int i = 0; i < width ; i++){
      for(int j = height - 1; j >= 0; j--){
        System.out.print(boardArray[i][j] + " | ");
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

  public void initializeBoard(){
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        boardArray[x][y].add(CellState.CELL_EMPTY); //ADD
      }
    }
  }

  public void addNew(){
    while (true){
      int x = MathUtils.random(0, width - 1);
      int y = MathUtils.random(0, height - 1);
      if(boardArray[x][y].contains(CellState.CELL_EMPTY)){
        if(MathUtils.random(0, 10) < 1){
          boardArray[x][y].clear();
          boardArray[x][y].add(CellState.CELL_4);
        }
        else {
          boardArray[x][y].clear();
          boardArray[x][y].add(CellState.CELL_2);
        }
        break;
      }
    }
  }
}
