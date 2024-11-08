FROM eclipse-temurin:21.0.4_7-jre-ubi9-minimal
WORKDIR /opt
ENV PORT 8090
EXPOSE 8090
COPY ./dear-comrade-spring-zero-to-hero-0.0.1-SNAPSHOT.jar /opt/dear-comrade-spring-zero-to-hero.jar
ENTRYPOINT exec java $JAVA_OPTS -jar dear-comrade-spring-zero-to-hero.jar