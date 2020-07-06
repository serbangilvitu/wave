#!/bin/bash

set -euo pipefail

IMAGE='docker.io/serbangilvitu/wave'
TAG=$(git rev-parse --verify HEAD)

for jdk in 'jdk8' 'jdk11' 'jdk14'; do
  docker build . -f docker/${jdk}/Dockerfile -t ${IMAGE}:${jdk}-${TAG}
  docker tag ${IMAGE}:${jdk}-${TAG} ${IMAGE}:${jdk}
  docker push ${IMAGE}:${jdk}-${TAG}
  docker push ${IMAGE}:${jdk}
done