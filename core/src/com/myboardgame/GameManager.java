package com.myboardgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.myboardgame.components.Result;
import com.myboardgame.components.ResultList;

import java.util.ArrayList;
import java.util.List;


public class GameManager {
    private static final String SOUND = "Sound";
    private static final String DATA_FILE_NAME = "results.json";
    public String userID;
    private Preferences PREFS;
    Json json;
    public boolean isGameOver;
    public ResultList results;
    public static final GameManager INSTANCE = new GameManager();

    private GameManager() {
        json = new Json();
        PREFS = Gdx.app.getPreferences(MyGdxGame.class.getSimpleName());
        userID ="Tilen";
        isGameOver = false;
        loadResults();
        results.sortList();
    }

    public void addResult(Result r){
        results.addResult(r);
        saveResults();
    }

    public void saveResults(){
        json = new Json();
        FileHandle file = Gdx.files.local(DATA_FILE_NAME);
        file.writeString(json.toJson(results), false);
    }

    public void loadResults() {
        json=new Json();
        FileHandle file = Gdx.files.local("results.json");
        if (!file.exists()) {
            results = new ResultList();
        } else {
            results = json.fromJson(ResultList.class, file.readString());
        }
    }

    public void toggleSound() {
        boolean sound = !isSound();
        PREFS.putBoolean(SOUND,sound);
        PREFS.flush();
    }

    public boolean isSound() {
        return PREFS.getBoolean(SOUND, true);
    }
}
