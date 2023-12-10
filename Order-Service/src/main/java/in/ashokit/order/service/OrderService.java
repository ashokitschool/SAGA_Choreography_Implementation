package in.ashokit.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.OrderStatus;
import in.ashokit.dto.PaymentStatus;
import in.ashokit.entity.PurchaseOrder;
import in.ashokit.entity.PurchaseOrderPayments;
import in.ashokit.repo.PurchaseOrderRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private PurchaseOrderRepository orderRepo;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Transactional
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
		PurchaseOrder purchaseOrder = orderRepo.save(convertDtoToEntity(orderRequestDto));
		orderRequestDto.setOrderId(purchaseOrder.getOrderId());
		orderRequestDto.setOrderStatus(OrderStatus.ORDER_CREATED.toString());
		kafkaTemplate.send("orders-topic", purchaseOrder);
		return purchaseOrder;
	}

	public List<PurchaseOrder> getAllOrders() {
		return orderRepo.findAll();
	}

	public PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
		PurchaseOrder entity = new PurchaseOrder();
		entity.setProductId(dto.getProductId());
		entity.setUserId(dto.getUserId());
		entity.setOrderStatus(OrderStatus.ORDER_CREATED.toString());
		entity.setPrice(dto.getAmount());
		return entity;
	}

	@KafkaListener(topics = "payments-topic",groupId = "ait-group")
	public void handleOrderStatusUpdate(PurchaseOrderPayments orderPayment) {
		String paymentStatus = orderPayment.getPaymentStatus();
		orderRepo.findById(orderPayment.getOrderId()).ifPresent(po -> {
			if (paymentStatus.equals(PaymentStatus.PAYMENT_COMPLETED.toString())) {
				po.setOrderStatus(OrderStatus.ORDER_COMPLETED.toString());
			} else {
				po.setOrderStatus(OrderStatus.ORDER_FAILED.toString());
			}
			po.setPaymentStatus(orderPayment.getPaymentStatus());
			orderRepo.save(po); // updating order status
		});
	}

	public OrderRequestDto convertEntityToDto(PurchaseOrder order) {
		OrderRequestDto dto = new OrderRequestDto();
		dto.setOrderId(order.getOrderId());
		dto.setUserId(order.getUserId());
		dto.setAmount(order.getPrice());
		dto.setProductId(order.getProductId());
		return dto;
	}
}
