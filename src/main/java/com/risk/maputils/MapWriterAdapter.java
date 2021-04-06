package com.risk.maputils;


import com.risk.models.Continent;

/**
 * Adapter class for MapWriter which will help in writing data back to map file.
 *
 * @author Chirag
 */
public class MapWriterAdapter extends MapWriter {

    private static ConquestMapWriter d_conquestMapWriter;

    /**
     * Parameterized constructor for MapWriterAdapter.
     * @param p_conquestMapWriter object of ConquestMapWriter
     */
    public MapWriterAdapter(ConquestMapWriter p_conquestMapWriter) {
        this.d_conquestMapWriter = p_conquestMapWriter;
    }


}
