package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.SavingAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISavingAccountService {
	public Flux<SavingAccount> findAll();
	public Mono<SavingAccount> findById(String id);
	public Mono<SavingAccount> save(SavingAccount savingAccount);
	public Mono<Void> delete(SavingAccount savingAccount);
	public Mono<SavingAccount> findByIdCustomerPerson(String idCustomerPerson);
}
