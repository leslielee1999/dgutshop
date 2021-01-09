FROM openjdk:8-jre
COPY application.yml application.yml
COPY dgutshop.jar dgutshop.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","dgutshop.jar"]