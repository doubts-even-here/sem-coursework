FROM openjdk:latest
COPY ./target/group2-coursework.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group2-coursework.jar", "db:3306", "10000"]