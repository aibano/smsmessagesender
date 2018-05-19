FROM anapsix/alpine-java:8
VOLUME /tmp
ADD ./build/libs/smsmessagesender-0.0.2-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]