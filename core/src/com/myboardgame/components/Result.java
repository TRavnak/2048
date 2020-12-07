package com.myboardgame.components;

public class Result {
    public int position;
    public String name;
    public int score;

    public Result(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
