package edu.umd.innovationlab.ragspringai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.stereotype.Service;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import java.util.List;
import org.springframework.core.io.ClassPathResource;


@Service
public class RAGService {

    private final EmbeddingModel embeddingModel;

    private final VectorStore vectorStore;


    final private Logger logger = LoggerFactory.getLogger(RAGService.class);

    public String getEmbedding(String text) {
        return embeddingModel.embed(text).toString();
    }

    public RAGService(EmbeddingModel embeddingModel, VectorStore vectorStore) {
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
    }

    public String loadData(String fileName) {
        DocumentReader pdfDocumentReader = new PagePdfDocumentReader(new ClassPathResource("data/"+fileName));
        List<Document> documents = pdfDocumentReader.get();
        vectorStore.add(documents);
        return "loaded " + fileName;

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

}
