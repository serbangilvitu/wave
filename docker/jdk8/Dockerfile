# Builder
FROM gradle:jdk8 as builder

ENV JAVA_OPTS ""
RUN mkdir /app

WORKDIR /app

ADD build.gradle /app/build.gradle
ADD src/ /app/src/

RUN gradle wrapper build

#----------------------------------

# Application

FROM alpine:3.12

RUN apk add openjdk8

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /wave
COPY --from=builder /app/build/libs/*.jar /wave/app.jar
ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -jar /wave/app.jar"]