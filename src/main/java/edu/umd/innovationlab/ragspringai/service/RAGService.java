package edu.umd.innovationlab.ragspringai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RAGService {

    final private Logger logger = LoggerFactory.getLogger(RAGService.class);

    public RAGService() {
    }

    public String doRAG(String input) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public String getEmbedding(String text) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public String chat(String text) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public String clearvs() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public String search(String text) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public String loadData(String fileName) {
        throw new UnsupportedOperationException("Not Implemented");
    }

}
