#FROM azul/zulu-openjdk:11
#WORKDIR /gateway
#COPY ./build/libs/gateway-0.0.1-SNAPSHOT.jar gateway.jar
#ENTRYPOINT ["java", "-jar", "gateway.jar"]
# CMD java -jar eureka.jar & java -jar gateway.jar

FROM azul/zulu-openjdk:11i
WORKDIR /first-service
COPY ./build/libs/first-service-0.0.1-SNAPSHOT.jar first-service-server.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "first-service-server.jar"]

