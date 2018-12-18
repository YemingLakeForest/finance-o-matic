package net.mingism.financeomatic.service;

import org.junit.Test;

public class AmexCsvImportServiceTest {


    private AmexCsvImportService cut = new AmexCsvImportService();

    @Test
    public void CsvImportTest() {
        cut.importFrom("src/test/resources/amex_file.csv");
    }
}