# Usamos Java 17 como base
FROM openjdk:17-jdk-slim

# Crear un volumen temporal
VOLUME /tmp

# Copiar el jar que se generará
ARG JAR_FILE=target/autoControlAPI.jar
COPY ${JAR_FILE} app.jar

# Comando para ejecutar el jar
ENTRYPOINT ["java","-jar","/app.jar"]
