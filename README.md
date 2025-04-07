# 단계별 정리
### 1차 요구사항 (기본 MVC 개발)
- [x] 온라인으로 무작위로 생성되는 곱셈 문제 사이트를 개발
- [x] 문제는 11~99 사이의 두 개의 정수의 곱으로 구성

#### 도메인 다이어그램
```mermaid
classDiagram
    class Multiplication {
        -int factorA
        -int factorB
    }
    
    class User {
        -String alias
    }
    
    class MultiplicationResultAttempt {
        -User user
        -Multiplication multiplication
        -int resultAttempt
    }
    
    class ResultResponse {
        -boolean correct
    }
    
    MultiplicationResultAttempt --> User
    MultiplicationResultAttempt --> Multiplication
```
#### 클래스 다이어그램
```mermaid
classDiagram
    class MultiplicationService {
        <<interface>>
        +createMultiplication() Multiplication
        +checkAttempt(MultiplicationResultAttempt) boolean
    }
    
    class MultiplicationServiceImpl {
        -RandomGeneratorService randomGeneratorService
        +createMultiplication() Multiplication
        +checkAttempt(MultiplicationResultAttempt) boolean
    }
    
    class RandomGeneratorService {
        <<interface>>
        +generateRandomFactor() int
    }
    
    class RandomGeneratorServiceImpl {
        +MINIMUM_FACTOR: int
        +MAXIMUM_FACTOR: int
        +generateRandomFactor() int
    }
    
    class MultiplicationController {
        -MultiplicationService multiplicationService
        +getRandomMultiplication() Multiplication
    }
    
    class MultiplicationResultAttemptController {
        -MultiplicationService multiplicationService
        +postResult(MultiplicationResultAttempt) ResponseEntity<ResultResponse>
    }
    
    MultiplicationServiceImpl ..|> MultiplicationService
    RandomGeneratorServiceImpl ..|> RandomGeneratorService
    MultiplicationServiceImpl --> RandomGeneratorService
    MultiplicationController --> MultiplicationService
    MultiplicationResultAttemptController --> MultiplicationService
```

### 2차 요구사항 (데이터베이스 연동)
- [x] 답안 제출 기록 저장
- [x] 최근 5개의 답안 제출 기록 확인 기능 추가
#### 클래스 다이어그램
```mermaid
classDiagram
    class MultiplicationService {
        <<interface>>
        +createMultiplication() Multiplication
        +checkAttempt(MultiplicationResultAttempt) boolean
    }
    
    class MultiplicationServiceImpl {
        -RandomGeneratorService randomGeneratorService
        -MultiplicationResultAttemptRepository attemptRepository
        -UserRepository userRepository
        -MultiplicationRepository multiplicationRepository
        +createMultiplication() Multiplication
        +checkAttempt(MultiplicationResultAttempt) boolean
        +getStatsForUser(String) List&lt;MultiplicationResultAttempt>
    }
    
    class RandomGeneratorService {
        <<interface>>
        +generateRandomFactor() int
    }
    
    class RandomGeneratorServiceImpl {
        +MINIMUM_FACTOR: int
        +MAXIMUM_FACTOR: int
        +generateRandomFactor() int
    }
    
    class MultiplicationController {
        -MultiplicationService multiplicationService
        +getRandomMultiplication() Multiplication
    }
    
    class MultiplicationResultAttemptController {
        -MultiplicationService multiplicationService
        +postResult(MultiplicationResultAttempt)
        +getStatistics(String)
    }

    class MultiplicationRepository {
        <<interface>>
    }

    class MultiplicationResultAttemptRepository {
        <<interface>>
        +findTop5ByUserAliasOrderByIdDesc(String)
    }

    class UserRepository {
        <<interface>>
        +findByAlias(String)
    }
    
    MultiplicationServiceImpl ..|> MultiplicationService
    RandomGeneratorServiceImpl ..|> RandomGeneratorService
    MultiplicationServiceImpl --> RandomGeneratorService
    MultiplicationController --> MultiplicationService
    MultiplicationResultAttemptController --> MultiplicationService
    MultiplicationServiceImpl --> MultiplicationResultAttemptRepository
    MultiplicationServiceImpl --> UserRepository
    MultiplicationServiceImpl --> MultiplicationRepository
```