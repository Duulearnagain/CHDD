package poly.store.interceptor;
//vì cotroller nào khi chạy cũng cần layout list category 
//nên cần phải viết trong 1 interceptor để cung cấp dự liệu cho tất cả controller
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import poly.store.service.CategoryService;
@Component
public class GlobalInterceptor implements HandlerInterceptor {
   @Autowired
   CategoryService categoryService;
   @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		request.setAttribute("cates",categoryService.findAll());
		//category trong layout
	}
}
