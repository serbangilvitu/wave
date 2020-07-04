# wave
Simple app which can be used to study the behaviour of containerized Java applications, running with different JDKs and configurations. 

## Endpoints
### /memory
With no parameter: shows the current request size, and details about the heap.

Set the requested size of the objects (in MiB)

`/memory?size=<int>`

E.g. set to 100 MiB

`/memory?size=100`


### /gc
Requests a garbage collection via System.gc()

## Actuator Endpoints
Exposes the following endpoints (via actuator)
### /actuator/env
### /actuator/health
### /actuator/metrics
### /actuator/prometheus

More about these:
https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-endpoints

# Usage
## Local
```
docker run -it --rm -e JAVA_OPTS="<java options>" -p 8080:8080 serbangilvitu/wave:<tag>
```
Available tags: `jdk8`, `jdk11`, `jdk14`

Example
```
docker run -it --rm -e JAVA_OPTS="-Xmx1024m" -p 8080:8080 serbangilvitu/wave:jdk8
```

You can identify the local process using
```
ps -C java -o pid,%cpu,%mem,args| grep wave
```