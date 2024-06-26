package com.galaxy13.games.tdserver.gamefield.zone;

public class Zone {
    int fieldRow;
    int fieldCol;
    ZoneType zoneType;

    public Zone(int fieldRow, int fieldCol, ZoneType zoneType) {
        this.fieldRow = fieldRow;
        this.fieldCol = fieldCol;
        this.zoneType = zoneType;
    }
}
