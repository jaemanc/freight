FROM openjdk:11
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app/freight/app.jar
ENTRYPOINT ["java","-jar","/app/freight/app.jar"]