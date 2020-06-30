# Builder
FROM gradle:jdk8 as builder

RUN mkdir /app

WORKDIR /app

RUN gradle wrapper
ADD build.gradle /app/build.gradle
ADD src/ /app/src/

RUN gradle wrapper build

#----------------------------------

# Application

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
