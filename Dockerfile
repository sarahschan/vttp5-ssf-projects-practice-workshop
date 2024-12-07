FROM openjdk:23-jdk AS builder


ARG APP_DIR=/app
WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src


RUN chmod a+x ./mvnw && ./mvnw clean package -Dmaven.test.skip=true


#ENV SERVER_PORT=3000

#EXPOSE ${SERVER_PORT}

#ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/vttp5a-practice-workshop-0.0.1-SNAPSHOT.jar


## Stage 2 ##
FROM openjdk:23-jdk

ARG DEPLOY_DIR=/app
WORKDIR ${DEPLOY_DIR}

COPY --from=builder /app/target/vttp5a-practice-workshop-0.0.1-SNAPSHOT.jar ssf-practice-workshop.jar

ENV SERVER_PORT=3000
EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar ssf-practice-workshop.jar