#!/bin/bash

# Build and run web-ui service
cd ./dashboard-ui && docker build -t dashboard-ui . && docker run -it --rm -v ${PWD}:/app -v /app/node_modules -p 3000:3000 -e CHOKIDAR_USEPOLLING=true dashboard-ui

