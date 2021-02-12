package com.risk.maputils;

import com.risk.models.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class MapWriter will write the map file when the user creates a map.
 * @author Rishabh
 */

public class MapWriter {
    /**
     * Below method writes the map details to map file,
     * @param map object of the map which is being processed..
     * @param file file path
     */
    public void writeMapFile(Map map, File file){

        FileWriter fileWriter;
        try {
            if (map == null){
                System.out.println("Map object is NULL! ");
            }

            String content = parseMapAndReturnString(map);
            fileWriter = new FileWriter(file, false);
            fileWriter.write(content);
            fileWriter.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * this method processes the map by calling three different methods and makes a string to be written in map file.
     * @param map object of the map which is processed
     * @return String to be written in the map file
     */

    private String parseMapAndReturnString(Map map) {

        StringBuilder content = new StringBuilder();

        return null;
    }
}