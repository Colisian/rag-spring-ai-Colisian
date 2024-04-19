package edu.umd.innovationlab.ragspringai;

import edu.umd.innovationlab.ragspringai.service.RAGService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationCommands {
    final RAGService ragService;

    public ApplicationCommands(RAGService ragService) {
        this.ragService = ragService;
    }

    @ShellMethod(key = "embed", value = "Get the embedding for the given input text")
    public String embed(@ShellOption String text) {
        return ragService.getEmbedding(text);
    }

    @ShellMethod(key = "chat", value = "Interact with the LLM Chat endpoint")
    public String chat(@ShellOption("Chat") String text) {
        return ragService.chat(text);
    }

    @ShellMethod(key = "load", value = "Load PDF document into vector store")
    public String load(@ShellOption String fileName) {
        return ragService.loadData(fileName);
    }

    @ShellMethod(key = "search", value = "Search for similar Documents in the vector store")
    public String search(@ShellOption String text) {
        return ragService.search(text);
    }

    @ShellMethod(key = "clearvs", value = "Delete all the documents in the vector store")
    public String clearvs() {
        return ragService.clearvs();
    }

    @ShellMethod(key = "rag", value = "Perform RAG on the input text")
    public String rag(@ShellOption String input) {
        return ragService.doRAG(input);
    }
}
