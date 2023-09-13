FROM openjdk:11
COPY ./build/libs/freight-0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]