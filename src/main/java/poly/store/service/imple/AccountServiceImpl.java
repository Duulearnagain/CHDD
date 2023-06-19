package poly.store.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDao;
import poly.store.entity.Account;
import poly.store.service.AccountService;

@Service
public class
AccountServiceImpl implements AccountService {
	@Autowired
	AccountDao accdao;

	@Override
	public Account findById(String username) {
		//thong qua DAO de tim kiem csdl
		return accdao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return accdao.findAll();
	}

	@Override
	public List<Account> getAdministrators() {
		return accdao.getAdministrators();
	}

}
