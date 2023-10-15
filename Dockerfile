FROM amazoncorretto:17-alpine3.17-jdk as build

COPY gradlew /build/gradlew
COPY gradle.properties /build/gradle.properties
COPY build.gradle.kts /build/build.gradle.kts
COPY settings.gradle.kts /build/settings.gradle.kts
COPY gradle /build/gradle
COPY dataTransfer /build/dataTransfer
COPY server /build/server

WORKDIR /build

RUN ./gradlew :server:buildFatJar

FROM amazoncorretto:17-alpine3.17
COPY --from=build /build/server/build/libs/server.fat.jar /app/server.fat.jar
WORKDIR /app
CMD ["java", "-jar", "server.fat.jar"]