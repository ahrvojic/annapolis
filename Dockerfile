FROM eclipse-temurin:19-alpine
COPY target/annapolis-*-standalone.jar /app/annapolis.jar
WORKDIR /app
EXPOSE 8080
CMD java $JAVA_OPTS -jar annapolis.jar
