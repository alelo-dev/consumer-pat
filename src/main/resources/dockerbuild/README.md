# Alelo Consumer Service

URI: http://localhost:8080
SWAGGER URI: http://localhost:8080/swagger-ui/#/


# Build
docker build -t diegosouzapw/apis:alelo-consumer-service-1.0 .
docker push diegosouzapw/apis:alelo-consumer-service-1.0

## Run 
docker run -d --name=alelo-consumer-service \
--network=host \
--restart unless-stopped \
-e SPRING_PROFILE=hml \
-e SPRING_RUN_JVM_ARGS="-Xms512m -Xmx2048m" \
diegosouzapw/apis:alelo-consumer-service-1.0

### Commands
docker logs -f alelo-consumer-service
docker exec -it alelo-consumer-service /bin/sh
docker rm -f alelo-consumer-service