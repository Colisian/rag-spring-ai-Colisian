# Retrieval-Augmented Generation (RAG)
## Introduction (Written by ChatGPT)
Retrieval-Augmented Generation (RAG) is a technique that combines the capabilities of a language model, like GPT, with an external knowledge retrieval system to enhance the model's ability to generate responses that are informed by a broader context than the model's internal knowledge. This approach uses a two-step process: first, when a query is received, relevant information is retrieved from a database or a document collection, and second, this information is fed into a language model to generate a response that is contextual and informed by the retrieved data.
Benefits of RAG over a standalone Language Model like ChatGPT:

1. Enhanced Accuracy and Relevance: RAG can pull in the most relevant and recent information from external sources, ensuring that the responses are not only contextually appropriate but also up-to-date. This is particularly useful for topics that are dynamic and rapidly changing.
2. Reduced Model Bias: By relying on external data sources for generating responses, RAG can potentially reduce biases that are inherent in the training data of the standalone models. The breadth of information it can access helps in providing a balanced view.
3. Scalability and Flexibility: RAG allows for scaling the knowledge of the language model without needing to retrain the model extensively. It can adapt to new information or changes in knowledge by simply updating the external sources it retrieves data from.
4. Efficiency in Learning: Instead of retraining the language model to learn new facts or correct errors, updates can be made to the retrieval database, which is generally less resource-intensive compared to training large language models.
5. Customization: The external knowledge base can be customized and curated to fit specific use cases or domains, providing more tailored responses that are not possible with a generalist model like ChatGPT.

Overall, RAG provides a powerful way to enhance the capabilities of traditional language models by integrating them with dynamic, external knowledge sources, leading to more accurate, reliable, and relevant outputs.

## RAG Service Overview
```mermaid
sequenceDiagram
    participant Client
    participant RAG Service
    participant Embedding Service
    participant PostgreSQL with pgvector
    participant LLM Service

    Client->>+RAG Service: Request content generation
    RAG Service->>+Embedding Service: Send text for embedding
    Embedding Service-->>-RAG Service: Return embedding
    RAG Service->>+PostgreSQL with pgvector: Retrieve relevant embeddings
    PostgreSQL with pgvector-->>-RAG Service: Return retrieved embeddings
    RAG Service->>+LLM Service: Send retrieved embeddings and prompt
    LLM Service-->>-RAG Service: Return generated content
    RAG Service-->>-Client: Deliver generated content
```
## RAG Project
### Overview
We are going to work incrementally to cover the following: 

- Check out project
- Embeddings - How to turn text into a numerical representation (vector).
- Vector Store - A place to keep your embeddings! 
   - How to store embeddings
   - How to search for similar embeddings
- LLM Chat - How to access a chat service
- Prompt Engineering - How to incorporate retrieved data into a prompt and generate a response.

### Check out the project
1. Open up a command shell  
2. Clone the repo https://gitlab.umd.edu/depstei2/rag-spring-ai  
   Go to a directory where you want to download the project and run   
   `git clone https://gitlab.umd.edu/depstei2/rag-spring-ai.git`
3. Build the project: `mvn package`
4. Run the project: `java -jar .\target\rag-spring-ai-0.0.1-SNAPSHOT.jar`
5. At the prompt, enter `help` and then `exit`

### Embeddings
Embedding overview: [embeddings.md](embeddings.md)



## Project
### Introduction
#### What is RAG?
We are going to build a simple RAG Service that takes input as text, retrieves related data and uses that to augment a generative response.
1. Check out the project
2. Set up a local vector database
   1. Get the PGVector Postgres docker image `docker pull ankane/pgvector:latest`
   2. Create a new container `docker run --name rag_postgres -e POSTGRES_PASSWORD=123umd -e POSTGRES_USER=postgres -p 5432:5432 -v ragpgdata:/var/lib/postgresql/data -d ankane/pgvector`
   3. Test connection to localhost:5432 in your DB IDE
   4. Run the following SQL to install PGVector and set up Spring AI table

3. 
#### Project Overview
#### Prerequisites
This Workshop has a lot of required software before we can get started. Please have these installed and configured prior to the workshop.

## Software Checklist
- [X] [Git](https://git-scm.com/downloads) - Version Control Manager. (MacOS should already have it, Windows can be downloaded in the Microsoft Store)
- [X] [Node.js](https://nodejs.org/en/download/current) - Needed for UMD AWS Authentication tool
- [X] [UMD AWS Cli Authentication](https://umd-dit.atlassian.net/wiki/spaces/PS/pages/25790559/AWS+CLI+Authentication) - Needed to Authenticate to AWS so we can use their AI services
- [X] [Java 21](https://jdk.java.net/21/) - Latest java program. Make sure it is in the path so if you run `java -version` it says version 21.x
- [X] [Maven](https://maven.apache.org/download.cgi) - A build and dependency/package management system for Java (like pip/npm)
- [X] [Docker / Docker Desktop](https://www.docker.com/products/docker-desktop/) - Used to set up a local Postgres Database with PGVector installed.
- [X] An IDE for Java such as [IntelliJ](https://www.jetbrains.com/idea/download/)
- [X] A Database IDE such as [DataGrip](https://www.jetbrains.com/datagrip/download/), DbVisualizer, or Toad

Run each of these commands and make sure there are no errors in the output.
* `git --version`
* `node --version`
* `npm --version`
* `umd_aws_auth -V`
* `java -version` &rarr; Must be version 21+ (If not please install and set the PATH in your system)
* `mvn -version`
* `docker --version`