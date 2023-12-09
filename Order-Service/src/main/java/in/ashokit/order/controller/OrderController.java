package in.ashokit.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.order.entity.PurchaseOrder;
import in.ashokit.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public PurchaseOrder createOrder(@RequestBody OrderRequestDto  orderRequestDto) {
		return orderService.createOrder(orderRequestDto);
	}

}
