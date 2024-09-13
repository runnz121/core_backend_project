#!/usr/bin/env bash
REPOSITORY=/home/ubuntu/kitten-api
# shellcheck disable=SC2164
cd $REPOSITORY

APP_NAME=deploy
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/deploy-0.0.1-SNAPSHOT.jar
#CURRENT_PID=$(pgrep -f $APP_NAME)

#if [ -z $CURRENT_PID ]
#then
#  echo "> 종료할것 없음."
#else
#  echo "> kill -9 $CURRENT_PID"
#  sudo kill -9 $CURRENT_PID
#  sleep 5
#fi
#echo "> kill port 8080"
#pid=$(lsof -i:8080 -t); kill -9 $pid

sudo killall java

echo "> $JAR_PATH start"
#sudo nohup java -jar /home/ubuntu/deploy_test/build/libs/deploy-0.0.1-SNAPSHOT.jar
#sudo nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null
sudo nohup java -jar /home/ubuntu/kitten-api/build/libs/deploy-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &
#sudo nohup java -jar /home/ubuntu/deploy_test/build/libs/deploy-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null