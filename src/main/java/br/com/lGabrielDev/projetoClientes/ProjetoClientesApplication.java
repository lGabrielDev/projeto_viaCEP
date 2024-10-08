package br.com.lGabrielDev.projetoClientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjetoClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoClientesApplication.class, args);
	}
}