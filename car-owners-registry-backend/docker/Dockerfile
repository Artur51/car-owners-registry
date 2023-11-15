FROM eclipse-temurin:17-jdk-alpine
COPY car-owners-registry-backend-1.0.0.jar app.jar

ENV PORT 80
EXPOSE 80

ENTRYPOINT ["java","-jar","/app.jar"]