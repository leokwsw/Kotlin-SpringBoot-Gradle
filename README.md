# Kotlin-SpringBoot-Gradle

### Default Config(File : [application.yml](src/main/resources/application.yml))

Port : 8080<br>
h2 console Url : (http://localhost:8080/h2-console)<br>
h2 url : jdbc:h2:file:./src/main/resources/data/db<br>
h2 username : sa<br>
h2 password : password

### Build Docker Image

```shell
./gradlew build && docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .
```

### Run Docker Image

```shell
docker run -p 8080:8080 springio/gs-spring-boot-docker
```
