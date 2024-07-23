FROM openjdk:21-alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/dear-comrade-spring-zero-to-hero-0.0.1-SNAPSHOT.jar.jar /opt/dear-comrade-spring-zero-to-hero.jar
ENTRYPOINT exec java $JAVA_OPTS -jar spring-boot-reactjs-integration-maven.jar