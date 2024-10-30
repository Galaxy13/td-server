package com.galaxy13.games.field;

import com.galaxy13.games.manager.Event;

public class TDField implements GameField{
    float xSize;
    float ySize;

    private TDField(float xSize, float ySize){
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public static GameField createBasicField(float xSize, float ySize){
        return new TDField(xSize, ySize);
    }

    @Override
    public void changeState(Event event){
    }
}
