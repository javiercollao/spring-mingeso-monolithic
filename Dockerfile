FROM openjdk:17
ARG JAR_FILE=target/MyTopEducationApp.jar
COPY ${JAR_FILE} MyTopEducationApp.jar
ENTRYPOINT ["java","-jar","/MyTopEducationApp.jar"]