package com.nttdata.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.bootcamp.models.SavingAccount;
import com.nttdata.bootcamp.repositories.ISavingAccountRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@EnableEurekaClient
@Slf4j
@SpringBootApplication
public class BootcampServiceProductSavingaccountApplication implements CommandLineRunner{
	
	@Autowired
	ISavingAccountRepo sarepo;
	
	@Autowired
	ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BootcampServiceProductSavingaccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		mongoTemplate.dropCollection("savingaccounts").subscribe();
		
		Flux.just(SavingAccount.builder()
				.idCustomerPerson("b1")
				.accountingBalance("100")
				.maintenance("2")
				.movementLimit("3")
				.build()).flatMap(bs->{
						return sarepo.save(bs);
				}).subscribe(s-> log.info("Se ingreso savingAccount: "+s));
	}

}
