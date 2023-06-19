package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("security/login/form")

	public String loginForm(Model model) {
		model.addAttribute("mess","Vui lòng đăng nhập");
		return"security/login";
	}

	@RequestMapping("security/login/success")

	public String loginSuccess(Model model) {
		model.addAttribute("mess","Đăng nhập thành công");
		return"security/login";
	}

	@RequestMapping("security/login/error")

	public String loginError(Model model) {
		model.addAttribute("mess","Sai thông tin đăng nhập");
		return"security/login";
	}

	@RequestMapping("security/unauthoried")

	public String unauthoried(Model model) {
		model.addAttribute("mess","Không có quyền truy xuất");
		return"security/login";
	}

	@RequestMapping("security/logoff/success")

	public String logoffsuccess(Model model) {
		model.addAttribute("mess","Bạn đã đăng xuất");
		return"security/login";
	}
}
