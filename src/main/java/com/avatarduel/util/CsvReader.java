package com.avatarduel.util;

/** CsvReader to load csv data from resource
 * 
 * @author K01_01_IF2210
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private String filePath;

    public CsvReader(String fp) {
        filePath = fp;
    }

    /** reads csv line by line from file.
     * 
     * @return An ArrayList of Array of string. Each Array of string is the result of the splitting of each line in the csv file.
     * @throws IOException
     */
    public ArrayList<String[]> readCsv() throws IOException {
        ArrayList<String[]> result = new ArrayList<String[]>();
        BufferedReader csvRead = new BufferedReader(new FileReader(filePath));
        String row = csvRead.readLine();
        if (row != null) { 
            // ignore first line 
            row = csvRead.readLine();
            while (row != null) {
                String[] data = row.split("	");
                result.add(data);
                row = csvRead.readLine();
            }
        }
        csvRead.close();
        return result;
    }
}