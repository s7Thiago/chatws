# Imagem base para o Maven (para compilar o projeto)
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto Maven para o container
COPY . .

# Executa o build do projeto usando Maven
RUN mvn clean package -DskipTests

# Imagem base para o runtime Java
FROM eclipse-temurin:17-jre

# Define o diretório de trabalho no container final
WORKDIR /app

# Copia o artefato gerado (JAR) do estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8085
EXPOSE 8085

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]