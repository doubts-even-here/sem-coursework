FROM openjdk:latest
COPY ./target/group2-coursework-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group2-coursework-1.0-SNAPSHOT-jar-with-dependencies.jar"]