FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY /lib/ojdbc8.jar /app/ojdbc8.jar
RUN mvn install:install-file -Dfile=/app/ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc -Dversion=8 -Dpackaging=jar
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8090
CMD ["java", "-jar", "/app/app.jar"]
