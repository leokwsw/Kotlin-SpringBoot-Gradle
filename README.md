# Kotlin-SpringBoot-Gradle

### Build Docker Image
```shell
./gradlew build && docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .
```

### Run Docker Image
```shell
docker run -p 8080:8080 springio/gs-spring-boot-docker
```
