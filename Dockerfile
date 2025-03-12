FROM openjdk:17

WORKDIR /app

COPY build/libs/kotlin-app.jar app.jar

CMD ["java", "-jar", "app.jar"]
