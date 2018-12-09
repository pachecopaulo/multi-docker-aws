FROM openjdk:10-jre
WORKDIR /usr/app
ARG JAR_FILE=build/libs/docker-aws-app.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","application.jar"]