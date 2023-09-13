FROM openjdk:11
VOLUME /tmp
COPY /app/freight/build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom" ,"-jar","/app.jar"]
