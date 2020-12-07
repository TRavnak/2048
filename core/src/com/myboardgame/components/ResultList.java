package com.myboardgame.components;

import java.util.ArrayList;
import java.util.Collections;

public class ResultList {
    public ArrayList<Result> list;

    public ResultList() {
        list = new ArrayList<Result>();
    }

    public void addResult(Result r){
        list.add(r);
        sortList();
    }

    @Override
    public String toString() {
        return "ResultList{" +
                "list=" + list +
                '}';
    }

    public void sortList(){
        for(int i = 0; i < list.size(); i++){
            for(int j = 1; j < list.size(); j++){
                if(list.get(j).score > list.get(j - 1).score){
                    Result tmp = list.get(j - 1);
                    list.remove(j - 1);
                    list.add(j, tmp);
                }
            }
        }
        for(int i = 0; i < list.size(); i++){
            list.get(i).position = i + 1;
            System.out.println(list.get(i));
        }
    }
}
