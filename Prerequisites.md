
## Software Checklist
- [X] [Git](https://git-scm.com/downloads) - Version Control Manager. (MacOS should already have it, Windows can be downloaded in the Microsoft Store)
- [X] [Node.js](https://nodejs.org/en/download/current) - Needed for UMD AWS Authentication tool
- [X] [UMD AWS Cli Authentication](https://umd-dit.atlassian.net/wiki/spaces/PS/pages/25790559/AWS+CLI+Authentication) - Needed to Authenticate to AWS so we can use their AI services
- [X] [Java 21](https://jdk.java.net/21/) - Latest java program. Make sure it is in the path so if you run `java -version` it says version 21.x
- [X] [Maven](https://maven.apache.org/download.cgi) - A build and dependency/package management system for Java (like pip/npm)
- [X] [Docker / Docker Desktop](https://www.docker.com/products/docker-desktop/) - Used to set up a local Postgres Database with PGVector installed.
- [X] An IDE such as [IntelliJ](https://www.jetbrains.com/idea/download/)

Run each of these commands and make sure there are no errors in the output.
* `git --version` 
* `node --version`
* `npm --version`
* `umd_aws_auth -V`
* `java -version` &rarr; Must be version 21+ (If not please install and set the PATH in your system)
* `mvn -version`
* `docker --version`