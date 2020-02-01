package br.com.jorgevmachado.sicapapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SicapApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SicapApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { }
}
