package net.mingism.financeomatic.repository;

import net.mingism.financeomatic.domain.StatementRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatementRecordRepository extends MongoRepository<StatementRecord, String> {
}
