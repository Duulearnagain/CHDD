package poly.store.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{
	@Query("select p from Product p where p.category.id=?1")
	List<Product> finByCategoryId(Optional<String> cid);

}
