FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar app.jar
COPY build/docs/asciidoc /app/static/docs
ENTRYPOINT ["java", "-jar", "app.jar"]