#Gradlew Build
FROM container-registry.oracle.com/java/openjdk:17-oraclelinux8

# Copy the native binary from the GraalVM image
COPY ./build/libs/*.jar /app/consumer-pat.jar

# Set the entry point to run the application
ENTRYPOINT ["java"]
CMD ["-jar", "/app/consumer-pat.jar"]