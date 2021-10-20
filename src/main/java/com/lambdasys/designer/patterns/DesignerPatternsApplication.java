package com.lambdasys.designer.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//anotacao e auto explicativa ... mas para quem nao conhece ela vai habilitar a compilacao/configuracao de clientes feign.
@EnableFeignClients
@SpringBootApplication
public class DesignerPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignerPatternsApplication.class, args);
	}

}
