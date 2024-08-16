package com.cliente.app.spring_boot_clienteapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootClienteappApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootClienteappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass1 = "user";
		String pass2 = "admin";

		System.out.println(passEncoder.encode(pass1));
		System.out.println(passEncoder.encode(pass2));
	}
}
