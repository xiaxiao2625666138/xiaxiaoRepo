FROM openjdk:8-jdk-alpine
EXPOSE 8086
COPY ./wordladder-0.0.1-SNAPSHOT.jar /home/wordladder.jar
COPY ./txtfile/dictionary.txt  src/txtfile/dictionary.txt
ENTRYPOINT [ "java", "-jar", "/home/wordladder.jar" ]
