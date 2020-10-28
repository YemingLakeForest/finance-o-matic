package net.mingism.financeomatic.service;

import net.mingism.financeomatic.domain.StatementRecord;

import java.util.List;

public interface SourceAndDump {

    List<StatementRecord> source();
    void dump(List<StatementRecord> records);
}
