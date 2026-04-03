@echo off
chcp 65001 > nul
echo 메인 컴퓨터 자체의 도커(로컬) 인프라로 스프링 부트를 실행합니다...
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
