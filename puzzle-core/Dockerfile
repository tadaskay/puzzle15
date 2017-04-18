FROM openjdk:8u121-jre

WORKDIR /opt/app
ADD build/libs/puzzle-core-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT java -jar app.jar
