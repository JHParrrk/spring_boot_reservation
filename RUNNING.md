# Spring Boot Application 실행 방법

이 문서는 Spring Boot 애플리케이션을 실행하는 방법에 대해 설명합니다.

---

## 1. Maven Wrapper로 실행하기

Maven Wrapper를 사용하면 빌드와 실행을 한 번에 처리할 수 있습니다. 아래 명령어를 실행하세요:

```bash
./mvnw spring-boot:run
```

Windows 환경에서는 다음 명령어를 사용하세요:

```bash
mvnw.cmd spring-boot:run
```

이 명령은 소스 코드를 자동으로 빌드한 후 애플리케이션을 실행합니다. 따라서 JAR 파일을 생성하지 않아도 됩니다.

---

## 2. JAR 파일로 실행하기

JAR 파일을 한 번 빌드한 후, 이후에는 빌드 과정을 생략하고 JAR 파일을 바로 실행할 수 있습니다.

### 2.1 JAR 파일 빌드

아래 명령어를 실행하여 JAR 파일을 생성합니다:

```bash
./mvnw clean package
```

Windows 환경에서는 다음 명령어를 사용하세요:

```bash
mvnw.cmd clean package
```

빌드가 완료되면 `target` 디렉토리에 실행 가능한 JAR 파일이 생성됩니다. 예: `target/demo-0.0.1-SNAPSHOT.jar`

### 2.2 JAR 파일 실행

아래 명령어를 실행하여 애플리케이션을 실행합니다:

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## 3. 환경별 실행 스크립트 (권장)

인프라(Docker) 위치에 따라 실행 환경을 분리하여 구동할 수 있도록 전용 스크립트를 제공합니다. NestJS의 `package.json` 스크립트처럼 간편하게 사용할 수 있습니다.

### 3.1 메인 컴퓨터(로컬) 도커 이용

메인 컴퓨터 본체에서 실행 중인 도커 인프라에 연결하여 실행합니다:

```bash
./run-local.cmd
```

### 3.2 서브 컴퓨터(192.168.0.10) 도커 이용

외부 서브 컴퓨터에 띄워둔 도커 인프라에 연결하여 실행합니다:

```bash
./run-subpc.cmd
```

---

## 4. 실행 중지하기

애플리케이션 실행을 중지하려면 실행 중인 터미널에서 `Ctrl + C`를 눌러 프로세스를 종료할 수 있습니다.

---

## 4. 주의사항

- 소스 코드를 수정한 경우, JAR 파일을 다시 빌드해야 변경 사항이 반영됩니다.
- 개발 중에는 `mvnw spring-boot:run` 명령을 사용하는 것이 편리합니다.
- 배포 시에는 JAR 파일을 빌드하여 실행하는 것이 일반적입니다.

---

## 5. Swagger UI 접속하기

Spring Boot 애플리케이션이 실행 중일 때, Swagger UI를 통해 API 문서를 확인할 수 있습니다. 아래 URL로 접속하세요:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

Swagger UI를 사용하면 애플리케이션의 API를 시각적으로 확인하고 테스트할 수 있습니다.

---

위 방법을 따라 애플리케이션을 실행할 수 있습니다. 추가로 궁금한 점이 있다면 언제든지 문의하세요!
