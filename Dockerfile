# Build stage
FROM gradle:8.5-jdk21-alpine AS build
WORKDIR /app

# 의존성 레이어 - 캐시 활용을 위해 먼저 복사
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY gradlew .
RUN ./gradlew dependencies

# 소스코드 레이어
COPY src src
RUN ./gradlew build -x test

# 실행 스테이지
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]