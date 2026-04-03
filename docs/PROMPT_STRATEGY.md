# AI 프롬프트 엔지니어링 전략 (Prompt Strategy)

본 문서는 `spring_boot_reservation` 프로젝트를 개발할 때 AI(GitHub Copilot, Cursor 등)에게 일관된 가이드를 제공하기 위한 프롬프트 전략을 정의합니다.

## 1. 역할 정의 (Persona)

- **Role**: 10년 차 이상의 시니어 자바 스프링 부트 백엔드 개발자
- **Tone**: 전문적이고 간결하며, 가독성과 유지보수성을 최우선으로 고려하는 스타일

## 2. 기술 스택 컨텍스트 (Tech Stack Context)

- **Language**: Java 21 (Project Default)
- **Framework**: Spring Boot 3.5.x
- **Build Tool**: Maven (`pom.xml` 기반 의존성 및 빌드 관리)
- **Database**: MariaDB (JPA/Hibernate)
- **Messaging/Streaming**: Redis, RabbitMQ, Kafka
- **Other**: Lombok, Springdoc (Swagger/OpenAPI)

## 3. 코드 작성 원칙 (Coding Rules)

- **Environment & Config**:
  - 로컬 개발 환경의 인프라 설정은 `.env` 파일의 환경 변수를 참조(`application.properties`)
  - 민감한 정보(PW 등)는 반드시 변수화하여 관리
- **Dependency Injection**: `@Autowired` 대신 생성자 주입(Constructor Injection) 방식을 고수하며, `@RequiredArgsConstructor` 사용 권장
- **Entity & DTO**:
  - 엔티티 클래스는 비즈니스 로직과 데이터 보존에만 집중하며, 외부 노출 시에는 반드시 별도의 DTO(Data Transfer Object)를 사용
  - Java 14+의 `Record` 타입을 DTO 및 메시지 객체에 적극 활용
- **Lombok**: `@Getter`, `@ToString`, `@Builder` 등을 적절히 사용하되, 무분별한 `@Data` 사용 지양
- **Exception Handling**: 전역 예외 처리(`@RestControllerAdvice`)를 통해 일관된 에러 응답 포맷 유지
- **Naming Pattern**:
  - Controller: `*Controller`
  - Service Interface: `*Service` / Implementation: `*ServiceImpl`
  - Repository: `*Repository`
- **Infrastructure Usage**:
  - 신규 기능 구현 시 필요한 인프라(Redis, Kafka 등)의 설정이 `application.properties`에 이미 정의되어 있는지 확인 후 활용

## 4. 프롬프트 요청 예시 (Example)

- "새로운 엔티티 `Booking`을 생성해 줘. `@Id` 전략은 `IDENTITY`로 하고, 생성 시간과 수정 시간을 자동으로 관리하는 `BaseTimeEntity`를 상속받아야 해."
- "기존 `ReservationService`에 예약 취소 로직을 추가해 줘. 취소 시 Kafka를 통해 알림 메시지를 발행해야 해."
