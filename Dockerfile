FROM bellsoft/liberica-openjdk-alpine-musl:20.0.1-10 as base
EXPOSE 8080

FROM alpine:3.12 as dependencies
RUN echo "https://apk.bell-sw.com/main" | tee -a /etc/apk/repositories
RUN wget -P /etc/apk/keys/ https://apk.bell-sw.com/info@bell-sw.com-5fea454e.rsa.pub
RUN apk add bellsoft-java20-lite maven
RUN mkdir /app
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

FROM dependencies as build
COPY src src
RUN mvn package -DskipTests

FROM base
COPY --from=build /app/target/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]