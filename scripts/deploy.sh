#!/usr/bin/env bash
REPOSITORY=/home/ubuntu/kitten-api/kitten-api
# shellcheck disable=SC2164
cd $REPOSITORY

APP_NAME=deploy
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/kitten-api-0.0.1-SNAPSHOT.jar


sudo killall java

echo "> $JAR_PATH start"
# shellcheck disable=SC2024
sudo nohup java -jar /home/ubuntu/kitten-api/kitten-api/build/libs/kitten-api-0.0.1-SNAPSHOT.jar > output.log 2>&1 &
