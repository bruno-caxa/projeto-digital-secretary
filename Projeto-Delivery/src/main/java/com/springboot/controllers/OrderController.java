package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.entities.Order;
import com.springboot.services.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/orderDelivered/{id_order}")
	public ModelAndView orderDelivered(@PathVariable Long id_order) {
		ModelAndView mav = new ModelAndView("admin/orders");
		Order order = orderService.findById(id_order);
		orderService.delivered(order);
		mav.addObject("orders", orderService.findAllPending());
		return mav;
	}
}
