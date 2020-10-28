package net.mingism.financeomatic.service;

import lombok.RequiredArgsConstructor;
import net.mingism.financeomatic.domain.StatementRecord;
import net.mingism.financeomatic.service.destination.MongoDestination;
import net.mingism.financeomatic.service.origin.AmexCsvImportService;
import net.mingism.financeomatic.service.origin.SantanderCsvImportService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportAndDumpService implements SourceAndDump {

    private final AmexCsvImportService amexCsvImportService;
    private final SantanderCsvImportService santanderCsvImportService;
    private final MongoDestination csvDestination;

    @PostConstruct
    public void importAndDump() {
        dump(source());
    }

    @Override
    public List<StatementRecord> source() {
        List<StatementRecord> amexRecords = amexCsvImportService.importFrom("src/test/resources/amex_file.csv");
        List<StatementRecord> santanderRecords = santanderCsvImportService.importFrom("src/test/resources/santander_file.csv");
        amexRecords.addAll(santanderRecords);
        return amexRecords;
    }

    @Override
    public void dump(List<StatementRecord> records) {
        csvDestination.save(records);
    }
}
