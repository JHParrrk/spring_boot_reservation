@echo off
chcp 65001 > nul
echo 서브 컴퓨터(192.168.0.10)의 도커 인프라로 스프링 부트를 실행합니다...
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=subpc
