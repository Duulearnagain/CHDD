package poly.store.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.ProductDao;
import poly.store.entity.Product;
import poly.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productDao.findById(id).get();
	}

	@Override
	public List<Product> finByCategoryId(Optional<String> cid) {
		return productDao.finByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {

		return productDao.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productDao.deleteById(id);
	}

}
