FROM  openjdk:17

WORKDIR /app

COPY /target/clientes_api.jar /app/clientes.jar

RUN mkdir ./relatorios 

EXPOSE 8080

CMD [ "java", "-jar", "/app/clientes.jar" ]