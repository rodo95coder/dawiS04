package com.nttdata.bootcamp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.models.SavingAccount;
import com.nttdata.bootcamp.repositories.ISavingAccountRepo;
import com.nttdata.bootcamp.services.ISavingAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountImpl implements ISavingAccountService {
	
	@Autowired
	ISavingAccountRepo pcrepo;

	@Override
	public Flux<SavingAccount> findAll() {
		return pcrepo.findAll();
	}

	@Override
	public Mono<SavingAccount> findById(String id) {
		return pcrepo.findById(id);
	}

	@Override
	public Mono<SavingAccount> save(SavingAccount savingAccount) {
		return pcrepo.save(savingAccount);
	}

	@Override
	public Mono<Void> delete(SavingAccount savingAccount) {
		return pcrepo.delete(savingAccount);
	}

	@Override
	public Mono<SavingAccount> findByIdCustomerPerson(String idCustomerPerson) {
		// TODO Auto-generated method stub
		return pcrepo.findByIdCustomerPerson(idCustomerPerson);
	}
	
}
