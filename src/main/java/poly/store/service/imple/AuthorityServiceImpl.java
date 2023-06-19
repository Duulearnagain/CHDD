package poly.store.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDao;
import poly.store.dao.AuthorityDao;
import poly.store.entity.Account;
import poly.store.entity.Authority;
import poly.store.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
@Autowired
AuthorityDao authorityDao;

@Autowired
AccountDao accountDao;

@Override
public List<Authority> findAll() {
	
	return authorityDao.findAll();
}

@Override
public Authority create(Authority auth) {
	
	return authorityDao.save(auth);
}

@Override
public void delete(Integer id) {
	
	authorityDao.deleteById(id);
	
}

@Override
public List<Authority> findAuthorities() {
	List<Account> accounts=accountDao.getAdministrators();
	return authorityDao.authoritiesOf(accounts);
}


}
