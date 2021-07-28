package com.experis.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.experis.load.service.LoadService;


@SpringBootApplication
@EnableScheduling
public class LoadApplication {
	@Autowired
	LoadService load;
	
	public static void main(String[] args) {
		SpringApplication.run(LoadApplication.class, args);
	}

	@Scheduled(cron = "24 * * * * ?") //https://crontab.guru/
	public void loadData() {
		load.loadTable();
	}
}
