[![DockerHub](https://img.shields.io/badge/Dockerhub-serbangilvitu%2Fwave-orange.svg)](https://hub.docker.com/r/serbangilvitu/wave)
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

# Prometheus

## Helm
In case you do not have helm installed, it can be quickly set up using
```
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash
helm repo add stable https://kubernetes-charts.storage.googleapis.com/
helm repo update
```
## Setup
```
helm install prometheus stable/prometheus -f k8s/prometheus/values.yaml
```

Expose the service using port forwarding
```
kubectl port-forward svc/prometheus-server 9090:80
```

## Validate setup
Go to http://localhost:9090/targets


## JVM Heap Allocation Demo
### Deploy Kubernetes demo app
The demo is using JDK8, but the same could be done for JDK11 or 14, by simply updating the image in deployment.yaml
```
kubectl apply -f k8s/wave-jdk8/
```

Expose the service using port forwarding
```
kubectl port-forward svc/wave-jdk8 8080:8080
```


### Prometheus Query
http://localhost:9090/graph?g0.range_input=5m&g0.stacked=1&g0.expr=sum(jvm_memory_used_bytes%7Barea%3D%22heap%22%2Capp%3D%22wave-jdk8%22%7D)&g0.tab=0

### Memory allocation demo
1) Allocate 300 MiB

http://localhost:8080/memory?size=300

2) Wait 1-2 minutes

3) Reduce object size to 100 MiB and invoke System.gc()

http://localhost:8080/memory?size=100

http://localhost:8080/gc

![JVM Heap Demo](https://github.com/serbangilvitu/wave/blob/master/jvm-heap-demo.png?raw=true)
