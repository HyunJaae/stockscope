FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY libs/*.jar app.jar
COPY docs/asciidoc /app/static/docs
ENTRYPOINT ["java", "-jar", "app.jar"]