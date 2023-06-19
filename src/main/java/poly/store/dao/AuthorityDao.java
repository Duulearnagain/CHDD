package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Account;
import poly.store.entity.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
	@Query("select distinct a from Authority a where a.account in ?1")
	List<Authority> authoritiesOf(List<Account> accounts);

}
