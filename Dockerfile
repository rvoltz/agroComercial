FROM docker.totvs.io/totvs-agro-base/totvs_agro_jdk_11

LABEL version="1.0.0" description="Recomendação"

ADD . /home/sources

WORKDIR /home/sources

RUN mvn clean package

FROM openjdk:11.0.5-jre-slim

COPY --from=0 /home/sources/target/recomendacao-1.0-SNAPSHOT.jar /home/app/app.jar

WORKDIR /home/app

# Abrir porta 8080
EXPOSE 8080

ENTRYPOINT java \
-Duser.country=BR \
-Duser.language=pt \
-Dspring.datasource.url=$DATABASE_URL \
-Dspring.datasource.username=$DATABASE_USER \
-Dspring.datasource.password=$DATABASE_PASSWORD \
-jar app.jar