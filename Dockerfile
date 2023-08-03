FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/SimCostVRB-0.0.1-SNAPSHOT.jar /app/SimCostVRB-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "SimCostVRB-0.0.1-SNAPSHOT.jar"]