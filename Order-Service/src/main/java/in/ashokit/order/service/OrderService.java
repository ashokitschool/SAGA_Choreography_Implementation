package in.ashokit.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.OrderStatus;
import in.ashokit.order.entity.PurchaseOrder;
import in.ashokit.order.repo.OrderRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderStatusPublishService orderStatusPublisher;

	@Transactional
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

		PurchaseOrder purchaseOrder = orderRepo.save(convertDtoToEntity(orderRequestDto));

		orderRequestDto.setOrderId(purchaseOrder.getOrderId());

		// produce event to kafka topic
		orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);

		return purchaseOrder;
	}
	
	public List<PurchaseOrder> getAllOrders(){
		return orderRepo.findAll();
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
