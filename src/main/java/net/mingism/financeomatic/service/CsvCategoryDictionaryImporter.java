package net.mingism.financeomatic.service;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class CsvCategoryDictionaryImporter implements CategoryDictionaryImporter {

    private String fileName;

    @Override
    public Map<String, String> importFile() {

        try {
            List<String[]> values = new CSVReader(new FileReader(fileName)).readAll();

            return values.stream().collect(toMap(value -> value[0], value -> value[1]));

        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
