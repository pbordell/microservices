FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S gateway && adduser -S gateway -G gateway
USER gateway:gateway

WORKDIR /app

COPY target/apiGateway-0.0.1-SNAPSHOT.jar ApiGateway.jar

ENTRYPOINT ["java", "-XX:+UseG1GC", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ApiGateway.jar"]