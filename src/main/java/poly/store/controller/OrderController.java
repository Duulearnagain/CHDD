package poly.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.service.OrderService;

@Controller
public class OrderController {


	@Autowired // lam viec voi order phai tiem oderservice vao
	OrderService orderService;
	@RequestMapping("order/checkout") //den trang dat hang
	public String checkout() {
		return"order/checkout";
	}
	@RequestMapping("order/list") //cac don hang da dat
	public String list(Model model, HttpServletRequest request) {
		String username=request.getRemoteUser();//lay user nguoi dang nhap thong qua remote
		model.addAttribute("orders",orderService.findByUsername(username));
		System.out.println("aa");
		return"order/list";
	}
	@RequestMapping("order/detail/{id}") //den chi tiet don hang da dat
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order",orderService.findById(id));
		System.out.println("b");
		return"order/detail";
	}
}
