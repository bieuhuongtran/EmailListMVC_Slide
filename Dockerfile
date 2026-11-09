FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -ntp -DskipTests clean package

FROM tomcat:9.0-jre17-temurin-noble

ENV CATALINA_OPTS="-Xms64m -Xmx256m"

RUN sed -i 's/port="8005"/port="-1"/' /usr/local/tomcat/conf/server.xml
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

