FROM eclipse-temurin:17
RUN mkdir -p /src/main/resources/static/user_data/
COPY build/libs/*.jar app.jar
COPY src/main/resources/static app/static
ENTRYPOINT ["java","-jar","/app.jar"]