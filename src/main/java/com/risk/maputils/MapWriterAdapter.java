package com.risk.maputils;

import com.risk.models.Map;

import java.io.File;

/**
 * Adapter class for MapWriter which will help in writing data back to map file.
 *
 * @author Chirag
 */
public class MapWriterAdapter extends MapWriter {

    private static ConquestMapWriter d_conquestMapWriter;

    /**
     * Parameterized constructor for MapWriterAdapter.
     *
     * @param p_conquestMapWriter object of ConquestMapWriter
     */
    public MapWriterAdapter(ConquestMapWriter p_conquestMapWriter) {
        this.d_conquestMapWriter = p_conquestMapWriter;
    }


    /**
     * This method overrides writes the writeMapFile method of MapWriter class.
     *
     * @param p_map  object of the map which is being processed.
     * @param p_file object of file to store map data.
     */

    public static void writeMapFile(Map p_map, File p_file) {
        d_conquestMapWriter.writeConquestMapFile(p_map, p_file);
    }

}
