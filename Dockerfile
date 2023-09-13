FROM openjdk:11
WORKDIR /app
COPY ./build/libs/freight-0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]