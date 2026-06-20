FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S struts && adduser -S struts -G struts
USER struts:struts

WORKDIR /app

COPY target/micro-report-struts.jar micro-report-struts.jar

ENTRYPOINT ["java", "-XX:+UseG1GC", "-Djava.security.egd=file:/dev/./urandom", "-jar", "micro-report-struts.jar"]