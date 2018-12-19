package net.mingism.financeomatic.service;

import org.junit.Before;
import org.junit.Test;

public class AmexCsvImportServiceTest {

    private AmexCsvImportService cut;

    @Before
    public void setup() {
        CategoryDictionaryImporter categoryDictionaryImporter = new CsvCategoryDictionaryImporter();
        categoryDictionaryImporter.setFileName("src/test/resources/category_dictionary.csv");

        cut = new AmexCsvImportService(categoryDictionaryImporter);
    }

    @Test
    public void CsvImportTest() {
        cut.importFrom("src/test/resources/amex_file.csv");
    }
}