package net.mingism.financeomatic.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StatementRecord {

    private String id;
    private Source source;
    private String vendor;
    private String category;
    private Instant date;
    private double amount;

}
