FROM gradle:5.3.1-jdk11 as builder

COPY --chown=gradle:gradle build.gradle settings.gradle /app/
WORKDIR /app
COPY --chown=gradle:gradle src src
RUN gradle bootJar --info

FROM openjdk:11-jre-slim

ENV STORE_PATH=/data
COPY --from=builder /app/build/libs/sparql-service.jar .
EXPOSE 8080
CMD ["java", "-jar", "sparql-service.jar"]
