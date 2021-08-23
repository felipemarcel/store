FROM openjdk
ADD /target/store-0.0.1-SNAPSHOT.jar api.jar
CMD java -jar api.jar
EXPOSE 8080/tcp
