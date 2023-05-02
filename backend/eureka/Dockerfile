FROM azul/zulu-openjdk:11
WORKDIR /eureka
COPY ./eureka/build/libs/eureka-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]
# CMD java -jar eureka.jar & java -jar gateway.jar
