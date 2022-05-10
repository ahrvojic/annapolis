FROM amazoncorretto:17-alpine

ADD target/annapolis-*.jar /app/annapolis.jar

WORKDIR /app

EXPOSE 8080

CMD java $JAVA_OPTS -jar annapolis.jar
