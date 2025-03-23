# Estágio 1: Construir a aplicação Spring Boot com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .

RUN --mount=type=cache,target=/root/.m2 mvn -f pom.xml clean package

# Estágio 2: Criar a imagem do jre
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar api.jar
RUN mkdir -p /var/log/seplag/api/

ENV JAVA_OPTS="-Xmx512m -Xms128m"
ENV TZ=America/Cuiaba

RUN locale-gen pt_BR.UTF-8
RUN update-locale LANG=pt_BR.UTF-8

ENV LANGUAGE pt_BR.UTF-8
ENV LC_ALL pt_BR.UTF-8

EXPOSE 8081

# Comando para executar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "api.jar"]