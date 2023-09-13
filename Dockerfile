FROM openjdk:11
WORKDIR /app
ARG JAR_FILE=build/libs/freight-0.1.jar
COPY ${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-jar","/app/freight/app.jar"]