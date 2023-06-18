@echo off
set DOCKER_BUILDKIT=0
set COMPOSE_DOCKER_CLI_BUILD=0

call rd /s /q build

call gradlew build || goto :onerror

copy build\libs\ZON-Kills.jar localDependencies\ZON-Kills.jar

call docker-compose build -m 2g || goto :onerror
call docker-compose down
call docker-compose up --abort-on-container-exit

exit /b

:onerror
echo Failed with error. Quiting batch...
exit /b %errorlevel%