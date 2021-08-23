#!/bin/bash

#mvn package

eval $(minikube docker-env)

docker build -t api-store-app .

docker images | grep api

eval $(minikube docker-env -u)