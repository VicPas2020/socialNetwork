FROM openjdk:11.0.15-jre-slim

COPY ./build/libs/app*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]