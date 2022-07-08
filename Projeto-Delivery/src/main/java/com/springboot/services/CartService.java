package com.springboot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.Food;
import com.springboot.entities.FoodOrder;

@Service
public class CartService {

	@Autowired
	private FoodService foodService;
	
	private List<FoodOrder> items = new ArrayList<>();
	
	public void addItem(Long id) {
		FoodOrder FoodOrder = new FoodOrder();
		Food food = foodService.findById(id);
		
		int control = 0;
		for(FoodOrder fb : items) {
			if(fb.getFood().getId().equals(food.getId())) {
				fb.setQtde(fb.getQtde()+1);
				fb.setTotalValue(fb.getUnitPrice() * fb.getQtde());
				control = 1;
				break;
			}
		}
		
		if(control == 0) {
			FoodOrder.setFood(food);
			FoodOrder.setQtde(1);
			FoodOrder.setTotalValue(food.getPrice());
			items.add(FoodOrder);
		}
	}
	
	public void emptyCart() {
		items.clear();
	}
	
	public void deleteItem(Long id) {
		Food food = foodService.findById(id);
		
		for(FoodOrder it : items) {
			if(it.getFood().getId().equals(food.getId())) {
				items.remove(it);
				break;
			}
		}
	}
	
	public List<FoodOrder> getItems() {
		return items;
	}
	 
	public Double totalValue() {
		Double total = 0d;
		for(FoodOrder fb : items) {
			total += fb.getTotalValue();
		}
		return total;
	}
	
	public void updateQuantity(Long id, Integer action) {
		Food food = foodService.findById(id);
		
		for(FoodOrder fb : items) {
			if(fb.getFood().getId().equals(food.getId())) {
				if(action.equals(0)) {
					fb.setQtde(fb.getQtde()-1);
					if(fb.getQtde().equals(0)) {
						items.remove(fb);
					}
					
				} else {
					fb.setQtde(fb.getQtde()+1);
				}
				fb.setTotalValue(fb.getUnitPrice() * fb.getQtde());
				break;
			}
		}
	}
	
}
