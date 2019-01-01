package net.mingism.financeomatic.service;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import net.mingism.financeomatic.domain.Source;
import net.mingism.financeomatic.domain.StatementRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;

@Service
@RequiredArgsConstructor
public class AmexCsvImportService implements CsvImportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmexCsvImportService.class);

    private static final DateTimeFormatter UTC_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy")
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(UTC);

    private final CategoryDictionaryImporter categoryDictionaryImporter;


    @Override
    public List<StatementRecord> importFrom(String csvFile) {


        Map<String, String> categoryDictionary = categoryDictionaryImporter.importFile();
        try {
            // todo: use that builder thingy
            List<String[]> values = new CSVReader(new FileReader(csvFile)).readAll();

            return values.stream().map(value -> {

                LOGGER.trace("Parsed csv values {}", Arrays.toString(value));

                StatementRecord statementRecord = new StatementRecord();
                statementRecord.setSource(Source.AMEX);
                statementRecord.setAmount(Double.valueOf(value[2].replace("\"", "").trim()));
                statementRecord.setVendor(value[3].trim());
                statementRecord.setDate(UTC_FORMATTER.parse(value[0], Instant::from));
                statementRecord.setId(value[1].replace("\"", "").split(" ")[1]);

                categoryDictionary.forEach((key, category) -> {
                    if (statementRecord.getVendor().contains(key)) {
                        statementRecord.setCategory(category);
                    }
                });

                return statementRecord;
            }).collect(Collectors.toList());

        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }
    }
}
