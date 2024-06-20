package edu.umd.innovationlab.ragspringai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.stereotype.Service;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import java.util.stream.Collectors;
import org.springframework.ai.chat.client.ChatClient;


@Service
public class RAGService {

    private final EmbeddingModel embeddingModel;

    private final VectorStore vectorStore;

    private final ChatClient chatClient;


    final private Logger logger = LoggerFactory.getLogger(RAGService.class);

    public String getEmbedding(String text) {
        return embeddingModel.embed(text).toString();
    }

    public RAGService(EmbeddingModel embeddingModel, VectorStore vectorStore, ChatClient.Builder chatClientBuilder) {
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
        this.chatClient = chatClientBuilder.build();
    }

    public String loadData(String fileName) {
        DocumentReader pdfDocumentReader = new PagePdfDocumentReader(new ClassPathResource("data/"+fileName));
        List<Document> documents = pdfDocumentReader.get();
        vectorStore.add(documents);
        return "loaded " + fileName;

    }

    public String search(String text) {
        List<Document> similarDocs = vectorStore.similaritySearch(text);
        return similarDocs.stream().map(Document::getFormattedContent).collect(Collectors.joining("\n"));
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
        return chatClient.prompt(new Prompt(text)).call().content();
    }

    public String clearvs() {
        throw new UnsupportedOperationException("Not Implemented");
    }


}
