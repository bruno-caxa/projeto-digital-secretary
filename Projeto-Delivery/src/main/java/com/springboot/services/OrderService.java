package com.springboot.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.FoodOrder;
import com.springboot.entities.Order;
import com.springboot.entities.OrderStatus;
import com.springboot.repositories.FoodOrderRepository;
import com.springboot.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAllPending() {
		return orderRepository.findAll()
							  .stream()
							  .filter(o -> o.getStatus() == OrderStatus.PENDING)
							  .collect(Collectors.toList());
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}
	
	public void delivered(Order order) {
		order.setStatus(OrderStatus.DELIVERED);
		orderRepository.save(order);
	}
	
	public void save(Order order) {
		List<FoodOrder> foodsSelected = cartService.getItems();
		
		for(FoodOrder fo : foodsSelected) {
			foodOrderRepository.save(fo);
		}
		
		Date date = Calendar.getInstance().getTime();
		order.setFoodOrder(foodsSelected);
		order.setDate(date);
		order.setStatus(OrderStatus.PENDING);
		orderRepository.save(order);
		cartService.emptyCart();
	}
}
