FROM openjdk:11
WORKDIR /app
ARG JAR_FILE=/build/libs/freight-0.1.jar
COPY ${JAR_FILE} /app/freight-0.1.jar
ENTRYPOINT ["java","-jar","/app/freight-0.1.jar"]
