package net.mingism.financeomatic.service.destination;

import net.mingism.financeomatic.domain.StatementRecord;

import java.util.List;

public interface Destination {
    void save(List<StatementRecord> statementRecords);
}
