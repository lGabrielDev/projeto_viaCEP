FROM  openjdk:17-ea-10-jdk

WORKDIR /app

COPY /target/clientes_api.jar /app/clientes.jar

RUN mkdir ./relatorios 

EXPOSE 8080

CMD [ "java", "-jar", "/app/clientes.jar" ]