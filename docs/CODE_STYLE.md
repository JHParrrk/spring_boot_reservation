# 코드 스타일 및 린트 전략 (Code Style & Linting)

본 문서는 `spring_boot_reservation` 프로젝트의 코드 일관성을 유지하기 위한 서식 및 스타일 규칙을 정의합니다.

## 1. Java 스타일 가이드

- **기본 가이드**: [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)를 기본으로 채택합니다.
- **인덴트(Indent)**: Space 4칸 사용을 권장합니다.
- **Import 최적화**: 사용하지 않는 import는 즉시 제거하며, 패키지 순서는 IDE 기본 설정을 따릅니다.

## 2. 정적 분석 도구 (Linting)

- **Checkstyle**: 빌드 시 `checkstyle` 플러그인을 활성화하여 코드 컨벤션을 검사합니다.
- **SonarLint**: 개별 IDE 단계에서 실시간 코드 품질 검사를 권장합니다.

## 3. API 문서화 전략

- **Springdoc OpenAPI**: 모든 Controller와 DTO에는 Swagger 어노테이션(`@Tag`, `@Operation`, `@Schema`)을 사용하여 문서를 자동 생성합니다.
- **Format**: API 응답은 항상 일정한 JSON 포맷(`Result<T>`)을 유지합니다.

## 4. IDE 설정

- **VS Code**: `EditorConfig` 확장 도구를 설치하여 팀원 간 일관된 들여쓰기 환경을 유지합니다.
- **Format on Save**: 파일 저장 시 자동으로 포맷팅이 수행되도록 설정을 활성화합니다.
