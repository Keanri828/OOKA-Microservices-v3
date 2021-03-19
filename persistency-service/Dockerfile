FROM adoptopenjdk:11-jre-hotspot
COPY target/persistency-service-0.0.1-SNAPSHOT.jar persistency-service-0.0.1-SNAPSHOT.jar
ADD data  C:/data
ENTRYPOINT ["java", "-jar", "persistency-service-0.0.1-SNAPSHOT.jar"]