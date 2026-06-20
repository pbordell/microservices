FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S quarkus && adduser -S quarkus -G quarkus
USER quarkus:quarkus

WORKDIR /app

COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/*.jar /app/
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/

ENTRYPOINT ["java", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager", "-jar", "quarkus-run.jar"]