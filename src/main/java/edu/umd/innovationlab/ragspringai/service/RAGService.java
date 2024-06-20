package edu.umd.innovationlab.ragspringai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import java.util.List;


@Service
public class RAGService {

    private final EmbeddingModel embeddingModel;


    final private Logger logger = LoggerFactory.getLogger(RAGService.class);

    public String getEmbedding(String text) {
        return embeddingModel.embed(text).toString();
    }

    public RAGService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public String doRAG(String input) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    /*
    public String getEmbedding(String text) {
        throw new UnsupportedOperationException("Not Implemented");
    }
*/
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
