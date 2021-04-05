package com.risk.maputils;


import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.risk.main.Main.d_Log;

/**
 * Helps in writing Map data back to conquest map file format
 *
 * @author Chirag
 */



public class ConquestMapWriter {



    /**
     * This method writes the map details to map file
     *
     * @param p_map  object of the map which is being processed.
     * @param p_file object of file to store map data.
     */
    public static void writeConquestMapFile(Map p_map, File p_file) {

        FileWriter l_fileWriter;
        try {
            if (p_map == null) {
                System.out.println("Map object is NULL! ");
            }

            //String l_content = parseConquestMapAndReturnString(p_map);
            l_fileWriter = new FileWriter(p_file, false);
            //l_fileWriter.write(l_content);
            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



}
