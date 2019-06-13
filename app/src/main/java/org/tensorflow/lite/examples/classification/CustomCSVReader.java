package org.tensorflow.lite.examples.classification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CustomCSVReader {

    private final InputStream is;
    List<String[]> rows = new ArrayList<>();

    public CustomCSVReader(InputStream is) {
        this.is = is;
    }

    public List<String[]> readCSV() throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String csvSplitBy = "\t";

        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] row = line.split(csvSplitBy);
            rows.add(row);
        }
        return rows;
    }

}
