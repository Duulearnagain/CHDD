package poly.store.service;

import java.util.List;

import poly.store.entity.Account;

public interface AccountService {

	Account findById(String username);//lay ra duoc dl

	public List<Account> getAdministrators();
	public List<Account>findAll();
}
