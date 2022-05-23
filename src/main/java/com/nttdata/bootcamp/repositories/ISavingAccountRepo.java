package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.SavingAccount;

import reactor.core.publisher.Mono;

public interface ISavingAccountRepo extends ReactiveMongoRepository<SavingAccount, String> {

	Mono<SavingAccount> findByIdCustomerPerson(String idCustomerPerson);

}
