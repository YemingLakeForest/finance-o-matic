package net.mingism.financeomatic.service;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import net.mingism.financeomatic.domain.StatementRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileImportAndDumpService {

    private static final Logger LOGGER = LoggerFactory.getLogger( FileImportAndDumpService.class );

    private final AmexCsvImportService amexCsvImportService;
    private final SantanderCsvImportService santanderCsvImportService;

    @PostConstruct
    public void importAndDump() {
        List<StatementRecord> amexRecords = amexCsvImportService.importFrom("src/test/resources/amex_file.csv");
        List<StatementRecord> santanderRecords = santanderCsvImportService.importFrom("src/test/resources/santander_file.csv");

        amexRecords.addAll(santanderRecords);

        try (Writer writer = new FileWriter("somefile.csv")){
            StatefulBeanToCsv<StatementRecord> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(amexRecords);
        }  catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }


    }
}
