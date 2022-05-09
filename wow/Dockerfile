# define base docker image
FROM openjdk:8
LABEL maintainer="com.hqz.wow"
ADD target/wow-admin-0.0.1-SNAPSHOT.jar wow-admin.jar
ENTRYPOINT ["java", "-jar","wow-admin.jar", "-Xmx512m"]