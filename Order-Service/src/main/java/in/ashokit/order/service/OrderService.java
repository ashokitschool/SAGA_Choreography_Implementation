package in.ashokit.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.OrderStatus;
import in.ashokit.order.entity.PurchaseOrder;
import in.ashokit.order.repo.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	

	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

		PurchaseOrder purchaseOrder = orderRepo.save(convertDtoToEntity(orderRequestDto));

		orderRequestDto.setOrderId(purchaseOrder.getOrderId());

		// produce event to kafka topic
		
		

		return purchaseOrder;
	}

	public PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
		PurchaseOrder entity = new PurchaseOrder();

		entity.setProductId(dto.getProductId());
		entity.setUserId(dto.getUserId());
		entity.setOrderStatus(OrderStatus.ORDER_CREATED);
		entity.setPrice(dto.getAmount());

		return entity;
	}

}
