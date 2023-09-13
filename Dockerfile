FROM openjdk:11
WORKDIR /app
ARG JAR_FILE=/build/libs/freight-0.1.jar
COPY . /freight-0.1.jar
ENTRYPOINT ["java","-jar","/freight-0.1.jar"]