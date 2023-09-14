FROM openjdk:11
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=regjergfreightpaww","-jar","app.jar"]