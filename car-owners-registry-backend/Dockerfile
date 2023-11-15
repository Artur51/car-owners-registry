FROM gradle:7.6.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src/
WORKDIR /home/gradle/src

RUN gradle build -x test --no-daemon 


FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/car-owners-registry-backend-1.0.0.jar /app.jar


ENV PORT 80
EXPOSE 80

ENTRYPOINT ["java","-jar","/app.jar"]