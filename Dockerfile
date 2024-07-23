FROM java:8
EXPOSE 8089
ADD target/demoapp.jar demoapp.jar
ENTRYPOINT ["java","-jar","demoapp.jar"]