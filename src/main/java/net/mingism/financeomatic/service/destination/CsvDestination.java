package net.mingism.financeomatic.service.destination;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import net.mingism.financeomatic.domain.StatementRecord;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@Slf4j
public class CsvDestination implements Destination {

    @Override
    public void save(List<StatementRecord> statementRecords) {
        try (Writer writer = new FileWriter("somefile.csv")) {
            StatefulBeanToCsv<StatementRecord> beanToCsv = new StatefulBeanToCsvBuilder<StatementRecord>(writer).build();
            beanToCsv.write(statementRecords);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
