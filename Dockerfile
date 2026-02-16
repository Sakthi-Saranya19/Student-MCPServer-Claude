FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/StudMCPServer0.0.1-SNAPSHOT.jar StudMCPServer0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","StudMCPServer0.0.1-SNAPSHOT.jar" ]