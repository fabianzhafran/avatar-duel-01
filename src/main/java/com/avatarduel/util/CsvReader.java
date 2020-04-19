package com.avatarduel.util;

import java.io.BufferedReader;
// import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// import java.util.List;

public class CsvReader {
    private String filePath;

    public CsvReader(String fp) {
        filePath = fp;
    }

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