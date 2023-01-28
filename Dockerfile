FROM amazoncorretto:19-alpine
ADD target/annapolis-*-SNAPSHOT.jar /app/annapolis.jar
WORKDIR /app
EXPOSE 8080
CMD java $JAVA_OPTS -jar annapolis.jar
