package com.myapp.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myapp.spring.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//http://localhost:8080/context/orderHome
	@GetMapping("/")
	public String orderHome() {
		return "orderHome";
	}
	@GetMapping("/viewOrders")
	public String viewOrders(Model model) {
		model.addAttribute("orders",orderService.getOrderDetails());
		return "viewOrders";
		
	}
	
}
