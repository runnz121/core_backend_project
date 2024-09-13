### CORE 모듈

- domain
  - 공통의 entity 가 선언됨
- common
  - 공통의 서비스가 선언됨
    - security
    - utils

### API 모듈

- kitten-beads-api
  - beads api 제공 모듈 

### EC2 배포 설치

- IAM EC2 역할 할당 -> s3FullAccess
- 다음의 명령을 실행
- sudo apt update
- sudo apt install ruby-full
- sudo apt install wget
- cd /home/ubuntu
- wget https://aws-codedeploy-ap-northeast-2.s3.ap-northeast-2.amazonaws.com/latest/install
- chmod +x ./install
- sudo ./install auto > /tmp/logfile
- sudo service codedeploy-agent status
- sudo apt install openjdk-21-jdk
