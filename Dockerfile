FROM openjdk:17

WORKDIR /app

COPY build/libs/kotlin-redis-cached-data-all.jar app.jar

CMD ["java", "-jar", "app.jar"]
