FROM openjdk:11-jre-slim

ENV SPRING_PROFILE             ${SPRING_PROFILE}
ENV SPRING_RUN_JVM_ARGS        ${SPRING_RUN_JVM_ARGS}

WORKDIR /
ADD consumer-pat-0.0.1-SNAPSHOT.jar root.jar
CMD java -jar -Dspring-boot.run.jvmArguments=${SPRING_RUN_JVM_ARGS} root.jar --spring.profiles.active=${SPRING_PROFILE}