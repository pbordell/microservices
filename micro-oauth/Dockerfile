FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S auth && adduser -S auth -G auth
USER auth:auth

WORKDIR /app

COPY target/micro-oauth-0.0.1-SNAPSHOT.jar micro-auth.jar

ENTRYPOINT ["java", "-XX:+UseG1GC", "-Djava.security.egd=file:/dev/./urandom", "-jar", "micro-auth.jar"]
