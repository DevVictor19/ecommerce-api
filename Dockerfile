FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

RUN bash -c "set -a && source .env && ./mvnw package && set +a"

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]