FROM openjdk:8-jdk-slim AS build

RUN apt-get update
RUN apt-get install -y maven

COPY . /app

WORKDIR /app

RUN mvn clean install

FROM openjdk:8-jre-slim
EXPOSE 8080

COPY --from=build /app/target/api-cadastro-1.0.0.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
