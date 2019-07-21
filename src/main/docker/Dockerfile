FROM java:8

EXPOSE 8080
EXPOSE 5005

ENV MONGO_HOST localhost
ENV MONGO_PORT 27017
ENV MONGO_DATABASE catering_db

ADD catering-service-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app.jar"]
