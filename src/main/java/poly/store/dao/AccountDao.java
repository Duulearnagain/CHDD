package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Account;

public interface AccountDao extends JpaRepository<Account, String>  {
	  @Query("select distinct ar.account from Authority ar")
	List<Account> getAdministrators();
	

}
