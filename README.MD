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
- sudo lsof -i :8080 # 포트 확인하기 


### 트러블 슈팅
1. 로컬에서는 정상 api 호출되나 ec2 에서 호출시 302 /login redirect 되는 현상
- 커밋 : 13c3698
- 원인 : H2 콘솔 접근을 위해 설정한 스프링 시큐리티 설정이 원인 
  - .headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
  )
- 해당 옵션은 csrf 와 same origin 정책을 허용 시킴
- 하지만 csrf 가 허용되면 302 redirect 되는 현상이 발생함
- 따라서 해당 옵션 disabled 처리함
- 의문 .. 해당 옵션은 최초 설정 후 변경한게 없는데, 왜 EC2 에 올렸을시 발생하는가..
- 참고
  - https://velog.io/@kide77/Spring-Boot-3.x-Security-%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%A0%95-%EB%B0%8F-%EB%B3%80%ED%99%94