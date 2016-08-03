FROM java:8
VOLUME /tmp
ADD maven/${project.build.finalName}.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE ${reserved.port.http}
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]