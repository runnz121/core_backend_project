# DIY KITTEN - Backend System


## 🧩 서비스 개요

DIY 인형 제작 서비스를 위한 백엔드 시스템을 구축하였습니다.  
MSA 전환을 고려한 모듈 아키텍처, CQRS 패턴, 고성능 캐시 전략, OAuth 인증 및 CI/CD 자동화까지 포함된 서비스입니다.

---

## ✨ 주요 기능

- 🔐 **OAuth 로그인 및 인증**
  - 카카오, 구글, 네이버 로그인 지원
  - SpEL 기반의 커스텀 인증 어노테이션으로 인증 로직 간소화

- 🧵 **인형 생성 정보 임시 저장**
  - 인형 파트별 좌표 기반 Redis 캐싱
  - API 기반으로 임시 저장/조회 기능 제공

- 📝 **게시판 캐싱 및 조회 성능 개선**
  - 게시글 생성일 기준 Redis score 사용
  - 날짜 기반 탐색 및 페이지네이션 성능 최적화

- 🧸 **아이템 캐싱 및 응답 속도 개선**
  - Caffeine + Redis를 활용한 하이브리드 캐시 구성
  - Redis Pub/Sub 기반 로컬 캐시 무효화 로직 구현

- 🚀 **CI/CD 파이프라인 구성**
  - GitHub Action → AWS CodeDeploy 연동으로 자동 배포 구축

---

## 🧱 프로젝트 구조

핵사고날 아키텍처(Hexagonal Architecture)와 CQRS 패턴을 기반으로 모듈을 분리하고, 추후 MSA 전환을 고려해 유연하게 구성했습니다.

- `core-common`: 공통 설정 및 상수 정의
- `core-domain`: 도메인 모델과 포트 정의
- `kitten-api`: 인형 관련 API 서비스 구현

📦 게시판 기능 예시 구조:
```

├── adapter
│   ├── in
│   │   └── web
│   │       └── BoardController.java         // REST API 요청을 처리
│   └── out
│       └── persistence
│           ├── BoardRepositoryImpl.java     // 도메인 포트
│           └── JpaBoardRepository.java      // Spring Data JPA 인터페이스
├── application
│   └── service
│       └── BoardQueryService.java            // 비즈니스 로직(UseCase)
└── domain
├── model
│   └── Board.java                        // 도메인 엔티티
└── port
├── in
│   └── command
│         └── BoardCommandRepository.java  // Repository 인터페이스 (포트)
├── out
└── └─── query
└── BoardQueryRepository.java

````

---

## 🧮 테이블 설계

- Entity 기반의 도메인을 정의하고, JPA를 통해 테이블 자동 생성
- 전체 스키마는 ERD 또는 개발 문서에서 관리 (미리보기 이미지 예시)

[테이블 설계]![Image](https://github.com/user-attachments/assets/1e8550e9-34e2-408d-a9b8-5105aeb63da9)

---

## 🛠 트러블슈팅

### 1️⃣ 아이템 등록 및 조회 최적화를 위한 로컬 캐시 무효화

- **문제**: 조회/등록의 책임 분리를 고려하던 중, 신규 등록 시 로컬 캐시 무효화 필요
- **해결**:
  - Redisson `RTopic` 기반 메시지 발행/구독
  - `ApplicationEventPublisher`로 이벤트 트리거 → 로컬 캐시 무효화
```
    public void publish(String message) {

        RTopic topic = redissonClient.getTopic(ITEM_TOPIC);
        
        long receivers = topic.publish(message);
        
        log.info("<REDISSON:PUBLISHER:메시지 발행> 메시지 수신 : {}", receivers);
    }
```

```
    public void subscribe() {
    
        RTopic topic = redissonClient.getTopic(ITEM_TOPIC);
        
        topic.addListener((MessageListener<String>) (channel, msg) -> {
        
            if (cacheManager.getCache(ITEM_CAFFEINE_CACHE) == null) return;
            cacheManager.getCache(ITEM_CAFFEINE_CACHE).evict(ITEM_CACHE_KEY);
            
            log.info("<REDISSON:SUBSCRIBER:수신> itemCaffeineCache 캐시 삭제");
        });
    }
```

---

### 2️⃣ Score 기반 게시판 조회 최적화

- **문제**: 최신 게시글 위주의 빠른 조회 필요
- **해결**:
  - 게시글 생성 시점으로 Redis Sorted Set에 Score 저장
  - Score 기반으로 페이지네이션 및 시간 탐색 성능 향상

---

### 3️⃣ EC2 환경에서 API 호출 시 `/login`으로 리다이렉트 발생

- **문제**: Spring Security의 `frameOptions` 설정으로 인해 CSRF 우회 발생
- **해결**: `csrf().disable()` + `frameOptions(sameOrigin)` 설정으로 문제 해결

```java
.headers(headersConfigurer -> headersConfigurer
.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)).disable()
````


---

## 🛠 사용 기술 스택

* **백엔드**: Spring Boot, Spring Security, JPA, QueryDSL
* **인프라**: AWS EC2, S3, Aurora MySQL, IAM, Route 53, Nginx, Redis
* **CI/CD**: GitHub Actions, AWS CodeDeploy

---
