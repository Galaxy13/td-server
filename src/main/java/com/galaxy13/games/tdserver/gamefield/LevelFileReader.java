package com.galaxy13.games.tdserver.gamefield;

import com.galaxy13.games.tdserver.gamefield.exceptions.BrokenLevelException;
import com.galaxy13.games.tdserver.gamefield.zone.Zone;
import com.galaxy13.games.tdserver.gamefield.zone.ZoneType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LevelFileReader {
    private static String zoneCoordinateDelimiter = ",";
    private static String coordinateTypeDelimiter = ":";
    private static String entryEndDelimiter = ";";
    private static String groupBeginRegex = "(?<=\\[)\\S+(?=])";
    private static String groupEndRegex = "(?<=\\[)\\%s(?=\\])";

    private static final Logger logger = LoggerFactory.getLogger(LevelFileReader.class);

    public LevelFileReader(String zoneCoordinateDelimiter, String coordinateTypeDelimiter, String entryEndDelimiter, String groupBeginRegex, String groupEndRegex) {
        LevelFileReader.zoneCoordinateDelimiter = zoneCoordinateDelimiter;
        LevelFileReader.coordinateTypeDelimiter = coordinateTypeDelimiter;
        LevelFileReader.entryEndDelimiter = entryEndDelimiter;
        LevelFileReader.groupBeginRegex = groupBeginRegex;
        LevelFileReader.groupEndRegex = groupEndRegex;
    }

    public static void readLevelFile(String fileName, Zone[][] field) throws IOException, BrokenLevelException{
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = Pattern.compile(groupBeginRegex).matcher(line);
                if (matcher.hasMatch()){
                    handleFileZoneGroup(bufferedReader, field);
                }
            }
        }
    }

    private static void handleFileZoneGroup(BufferedReader bufferedReader, Zone[][] zone) throws IOException, BrokenLevelException {
        String zoneLine;
        while ((zoneLine = bufferedReader.readLine()) != null){
            String[] tempLine = zoneLine.split(coordinateTypeDelimiter);
            int row;
            int col;
            ZoneType zoneType;
            if (tempLine.length == 2 && tempLine[tempLine.length - 1].endsWith(entryEndDelimiter)){
                String[] coordinates = tempLine[0].split(zoneCoordinateDelimiter);
                if (coordinates.length == 2){
                    try {
                        row = Integer.parseInt(coordinates[0]);
                        col = Integer.parseInt(coordinates[1]);
                        zoneType = ZoneType.valueOf(tempLine[1].substring(0, tempLine[1].length() - 1));
                    } catch (NumberFormatException e) {
                        throw new BrokenLevelException("Broken level exception. Reason: broken zone coordinate");
                    }
                    if (row < zone.length && col < zone.length && row >= 0 && col >= 0){
                        if (zone[row][col] == null){
                            logger.warn("Writing into already existing field cell. Zone coordinate: row={}, col={}", row, col);
                        }
                        zone[row][col] = new Zone(row, col, zoneType);
                    } else {
                        throw new BrokenLevelException("Wrong coordinate parsed");
                    }
                } else {
                    throw new BrokenLevelException("More than one zone coordinate in entry");
                }
            } else {
                throw new BrokenLevelException("Wrong entry format");
            }
        }
    }
}
