FROM openjdk:11

WORKDIR /app/server/chatws

COPY . .

RUN apt-get update -y \
    && apt-get install vim -y

EXPOSE 8085

CMD ./mvnw clean package && java -jar target/chatws-0.0.1-SNAPSHOT.jar