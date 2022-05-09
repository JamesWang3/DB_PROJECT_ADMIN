# define base docker image
FROM openjdk:8
LABEL maintainer="com.hqz.wow"
ADD target/wow-0.0.1-SNAPSHOT.jar wow-docker-0.jar
ENTRYPOINT ["java", "-jar","wow-docker-0.jar"]