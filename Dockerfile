FROM openjdk:17-slim

ARG VERSION

EXPOSE 8081

ADD target/catalogmicroservice-$VERSION.jar app.jar

CMD ["java", "-jar", "app.jar"]