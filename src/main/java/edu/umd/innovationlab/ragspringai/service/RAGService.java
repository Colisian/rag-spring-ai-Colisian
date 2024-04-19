package edu.umd.innovationlab.ragspringai.service;

import edu.umd.innovationlab.ragspringai.reader.CsvDocumentReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RAGService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final EmbeddingClient embeddingClient;
    private final JdbcTemplate jdbcTemplate;

    final private Logger logger = LoggerFactory.getLogger(RAGService.class);

    public RAGService(ChatClient chatClient, VectorStore vectorStore, EmbeddingClient embeddingClient, JdbcTemplate jdbcTemplate) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
        this.embeddingClient = embeddingClient;
        this.jdbcTemplate = jdbcTemplate;
    }

//    public void loadDocuments(){
//        List <Document> documents = List.of(
//                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
//                new Document("The World is Big and Salvation Lurks Around the Corner"),
//                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));
//        // Add the documents to PGVector
//        vectorStore.add(documents);
//    }


    public String doRAG(String input) {
//        List<Document> similarDocuments = vectorStore.similaritySearch(input);
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest
                .query(input)
                .withSimilarityThreshold(0.5));
        Prompt prompt = generatePrompt(input, similarDocuments);
        logger.info("Prompt: \n" + prompt.getContents());
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

    private Prompt generatePrompt(String input, List<Document> similarDocuments) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Answer the question based on the context below. Keep the answer short and concise. Respond "Unsure about answer" if not sure about the answer.

                Context: {context}

                Question: {question}

                Answer:
                """);
        String context = similarDocuments.stream().map((Document::getContent)).collect(Collectors.joining("\n"));
        return promptTemplate.create(Map.of("context", context, "question", input));

    }


    public String getEmbedding(String text) {
        return embeddingClient.embed(text).toString();
    }

    public String chat(String text) {
        return chatClient.call(text);
    }

    public String clearvs() {
        int deleted = jdbcTemplate.update("DELETE FROM " + PgVectorStore.VECTOR_TABLE_NAME);
        return "Deleted " + deleted + " rows";
    }

    public String search(String text) {
        return vectorStore.similaritySearch(text).toString();
    }

    public String loadData(String fileName) {
        if (fileName.endsWith(".pdf")) {
            return loadPdf(fileName);
        } else if (fileName.endsWith(".csv")) {
            return loadCsv(fileName);
        }
        return "Unknown file format: " + fileName;

    }

    private String loadPdf(String fileName) {
        DocumentReader pdfDocumentReader = new PagePdfDocumentReader(new ClassPathResource("data/"+fileName));
        vectorStore.add(pdfDocumentReader.get());
        return "loaded " + fileName;
    }

    private String loadCsv(String fileName) {
        DocumentReader csvDocumentReader = new CsvDocumentReader(new ClassPathResource("data/"+fileName));
        List<Document> documents = csvDocumentReader.get();
        for(List<Document> documentChunk:chunkList(documents, 20)) {
            vectorStore.add(documentChunk);
            logger.info("Loading..."+ StringUtils.truncate(documentChunk.getFirst().getContent()), 20);
        }
        return "loaded " + fileName;
    }


    public static <T> List<List<T>> chunkList(List<T> inputList, int chunkSize) {
        // List to hold all the chunks
        List<List<T>> chunkedLists = new ArrayList<>();

        // Variable to track the number of elements processed
        int totalSize = inputList.size();

        for (int i = 0; i < totalSize; i += chunkSize) {
            // Get the min value between the start of the next chunk and the total size
            int end = Math.min(totalSize, i + chunkSize);

            // Add the sublist to chunked lists
            chunkedLists.add(new ArrayList<>(inputList.subList(i, end)));
        }

        return chunkedLists;
    }
}
