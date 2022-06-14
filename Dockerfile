FROM openjdk:17.0.2-jdk
ARG JAR_FILE=/build/libs/*jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dlog4j2.log_file_path=/var/log/app.log","-jar", "/app.jar"]