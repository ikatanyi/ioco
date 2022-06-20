FROM adoptopenjdk/openjdk11-openj9:alpine-slim as bulid
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk/openjdk11-openj9:alpine-slim
WORKDIR application
COPY --from=bulid application/dependencies/ ./
COPY --from=bulid application/spring-boot-loader/ ./
COPY --from=bulid application/snapshot-dependencies/ ./
COPY --from=bulid application/application/ ./
RUN apk add --no-cache tzdata ttf-dejavu && ln -sf /usr/share/zoneinfo/Africa/Nairobi /etc/localtime && echo "Africa/Nairobi" > /etc/timezone
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]