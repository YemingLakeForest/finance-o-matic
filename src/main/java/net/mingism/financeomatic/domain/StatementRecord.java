package net.mingism.financeomatic.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Getter
@Setter
public class StatementRecord {

    @Id
    private String id;
    private Source source;
    private String vendor;
    private String category;
    private Instant date;
    private double amount;

}
