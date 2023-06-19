package poly.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.entity.Product;
import poly.store.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
@RequestMapping("product/list")
public String list(Model model,
		@RequestParam("cid") Optional<String>cid) {
	if (cid.isPresent()) {
		List<Product> listpr = productService.finByCategoryId(cid);
		model.addAttribute("items", listpr);
	} else {
		List<Product> listpr = productService.findAll();
		model.addAttribute(""
				+ "items", listpr);
	}
	return "product/list";
}

@RequestMapping("product/detail/{id}")
public String detail( Model model, 
		@PathVariable("id") Integer id) {
     Product item= productService.findById(id);
     model.addAttribute("item",item);
	return "product/detail";
}
}
