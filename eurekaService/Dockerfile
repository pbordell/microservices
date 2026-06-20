FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/eurekaService-*.jar eureka-service.jar

ENTRYPOINT ["java", "-jar", "eureka-service.jar"]