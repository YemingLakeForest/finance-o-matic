package net.mingism.financeomatic.service.destination;

import net.mingism.financeomatic.domain.StatementRecord;
import net.mingism.financeomatic.repository.StatementRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDestination implements Destination {

    @Autowired
    private StatementRecordRepository statementRecordRepository;

    @Override
    public void save(List<StatementRecord> statementRecords) {
        statementRecordRepository.saveAll(statementRecords);
    }
}
