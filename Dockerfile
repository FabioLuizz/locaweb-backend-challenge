FROM openjdk:17-jdk
VOLUME /TMP

EXPOSE 8080

ARG JAR_FILE=target/locaweb-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]