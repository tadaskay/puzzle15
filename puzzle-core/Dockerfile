FROM openjdk:8u121-jdk

ENV BUILD_HOME=/opt/build
WORKDIR $BUILD_HOME

ADD . $BUILD_HOME
RUN ["chmod", "+x", "gradlew"]
RUN ["./gradlew", "build"]
