package edu.umd.innovationlab.ragspringai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RagSpringAiApplication {



    public static void main(String[] args) {
		SpringApplication.run(RagSpringAiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(ragService.doRAG(args[0]));
//	}
}
