package edu.umd.innovationlab.ragspringai.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDocumentReader implements DocumentReader {
    private Resource resource;

    public CsvDocumentReader(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Document> get() {
        List<Document> documents = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
            try (CSVParser csvParser = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(reader)) {
                for (CSVRecord record : csvParser) {
                    String documentJson = objectMapper.writeValueAsString(record.toMap());
                    documents.add(new Document(documentJson));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading resource:" + resource, e);
        }
        return documents;
    }
}
