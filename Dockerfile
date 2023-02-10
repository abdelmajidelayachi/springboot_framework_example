FROM openjdk:17
#make app directory
RUN mkdir /app
ADD target/mypet-3.0.1.jar /app/mypet.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mypet.jar"]
