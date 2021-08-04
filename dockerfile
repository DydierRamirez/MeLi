FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080
COPY ./target/ipvalidator-0.0.1-SNAPSHOT.jar ipvalidator-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/ipvalidator-0.0.1-SNAPSHOT.jar"]