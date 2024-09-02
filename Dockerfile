# the first stage of our build will extract the layers
FROM  eclipse-temurin:21-jre-ubi9-minimal as builder
WORKDIR application
ARG JAR_FILE=target/dear-comrade-spring-zero-to-hero-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar.jar
RUN java -Djarmode=layertools -jar application.jar.jar extract

# the second stage of our build will copy the extracted layers
FROM  eclipse-temurin:21-jre-ubi9-minimal
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]