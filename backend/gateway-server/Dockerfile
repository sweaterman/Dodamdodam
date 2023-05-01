FROM azul/zulu-openjdk:11
WORKDIR /spring
COPY ./eureka/build/libs/eureka-0.0.1-SNAPSHOT.jar eureka.jar
COPY ./gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]
ENTRYPOINT ["java", "-jar", "gateway.jar"]

