FROM java:8u45-jre
LABEL authors="bruce"
EXPOSE 9292

VOLUME /temp
ADD bee-admin.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "/app.jar"]