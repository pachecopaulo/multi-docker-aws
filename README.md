# multi-docker-aws

This project is a PoC intended to gain knowledge with Docker, Kubernets and AWS.  

### Stack
   * Kotlin
   * Spring Boot
   * Redis DB
   * NGINX
   * Postgres
   * React JS
   * Docker
   * Gradle

### Gradle Tasks

| Task                 |      Command            |
|--------------------  |-------------------------|
| Build docker images  | ./gradlew build docker  |
| Show linter errors   | ./gradlew lintKotlin    |
| Fix linter errors    | ./gradlew formatKotlin  |

## Docker Compose

The docker-compose has been defined to configure the multi docker images available in this project.

## Running the application in the docker

First you have to run ```./gradlew build docker``` command in order to assembly the components to be picked up by the correctly by the Dockerfile configuration.

Once the above step is complete, you can issue the command: ```docker-compose up --build```
   
## NGINX
The Nginx has been configured to forward the requests between backend and frontend. The requests prefixed with **/api** will be forward to the backend service otherwise the React JS module will handle the requests.
