package com.sicredi.SincronizacaoReceita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.sicredi.SincronizacaoReceita.HelloService;
import com.sicredi.SincronizacaoReceita.DefaultHelloService;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SincronizacaoReceitaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);
	}

	@Bean
	public HelloService getHelloService(){
		return  new DefaultHelloService();
	}

	@Override
	public void run(String... args) throws Exception {
		getHelloService().hello();
	}
}
