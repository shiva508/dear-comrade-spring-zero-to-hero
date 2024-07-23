FROM openjdk:21-alpine
WORKDIR /opt
ENV PORT 8084
EXPOSE 8084
COPY ./target/dear-comrade-spring-zero-to-hero-0.0.1-SNAPSHOT.jar /opt/dear-comrade-spring-zero-to-hero.jar
ENTRYPOINT exec java $JAVA_OPTS -jar dear-comrade-spring-zero-to-hero.jar
