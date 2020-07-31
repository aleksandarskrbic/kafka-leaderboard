#!/bin/bash

# Run Services
./gradlew clean build
cd ./ingestion-service && ./gradlew clean build bootrun &
cd ./aggregation-service && ./gradlew clean build bootrun &