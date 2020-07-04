#!/bin/bash

set -euo pipefail

TAG=$(git rev-parse --verify HEAD)
for jdk in 'jdk8' 'jdk11' 'jdk14'; do
  docker build . -f docker/${jdk}/Dockerfile -t serbangilvitu/wave:${TAG}
  docker tag serbangilvitu/wave:${TAG} serbangilvitu/wave:${jdk}
  docker push serbangilvitu/wave:${TAG}
  docker push serbangilvitu/wave:${jdk}
done