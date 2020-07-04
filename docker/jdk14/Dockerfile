# Builder
FROM gradle:jdk14 as builder

ENV JAVA_OPTS ""
RUN mkdir /app

WORKDIR /app

ADD build.gradle /app/build.gradle
ADD src/ /app/src/

RUN gradle wrapper build

#----------------------------------

# Application

FROM alpine:edge

RUN echo "@testing http://nl.alpinelinux.org/alpine/edge/testing" | tee -a /etc/apk/repositories && \
    apk add openjdk14@testing

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /wave
COPY --from=builder /app/build/libs/*.jar /wave/app.jar
ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -jar /wave/app.jar"]