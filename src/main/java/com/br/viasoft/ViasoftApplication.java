package com.br.viasoft;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.br.viasoft.nfe.service.NfeAvalibleStatusService;

@SpringBootApplication
@EnableScheduling
public class ViasoftApplication {
	
	@Autowired
	private NfeAvalibleStatusService service;
	
	private final long SEGUNDO = 10000;
	private final long FIVE_MINUTS = 300000;

	public static void main(String[] args) {
		SpringApplication.run(ViasoftApplication.class, args);
		
	}

	@Scheduled(fixedDelay = FIVE_MINUTS)
	public void checkNfeAvalibleStatusService() throws IOException {
		service.statusAvaliabelService();
	}
}
