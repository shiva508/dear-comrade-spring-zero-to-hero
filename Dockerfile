FROM eclipse-temurin:21.0.4_7-jre-ubi9-minimal
WORKDIR /opt
ENV PORT 8090
EXPOSE 8090
COPY ./home/runner/work/dear-comrade-spring-zero-to-hero/dear-comrade-spring-zero-to-hero/dear-comrade-spring-zero-to-hero-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java $JAVA_OPTS -jar dear-comrade-spring-zero-to-hero.jar