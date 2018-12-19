package net.mingism.financeomatic.service;

import java.util.Map;

public interface CategoryDictionaryImporter {

    Map<String, String> importFile();
    void setFileName(String fileName);
}
