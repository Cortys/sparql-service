FROM gradle:5.3.1-jdk11 as builder

COPY --chown=gradle:gradle build.gradle settings.gradle /app/
WORKDIR /app
RUN gradle dependencies --info

COPY --chown=gradle:gradle src ./src

RUN gradle bootJar --info

FROM openjdk:11-jre
ENV STORE_PATH=/data
COPY --from=builder /app/build/libs/sparql-service.jar .

CMD ["java", "-jar", "sparql-service.jar"]
