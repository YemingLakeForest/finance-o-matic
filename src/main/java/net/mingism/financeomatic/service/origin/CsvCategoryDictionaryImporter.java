package net.mingism.financeomatic.service.origin;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class CsvCategoryDictionaryImporter implements CategoryDictionaryImporter {

    @Value("${category.dictionary.file.csv}")
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
