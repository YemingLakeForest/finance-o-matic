package net.mingism.financeomatic.service;

import net.mingism.financeomatic.domain.StatementRecord;

import java.util.List;

public interface CsvImportService {

    List<StatementRecord> importFrom(String csvFile);

}
