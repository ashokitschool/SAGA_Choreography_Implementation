package in.ashokit.order.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.OrderStatus;
import in.ashokit.dto.PaymentStatus;
import in.ashokit.order.entity.PurchaseOrder;
import in.ashokit.order.repo.OrderRepository;
import in.ashokit.order.service.OrderStatusPublishService;

@Configuration
public class OrderStatusUpdateHandler {

	@Autowired
	private OrderRepository repo;

	@Autowired
	private OrderStatusPublishService publisher;

	public void updateOrder(int id, Consumer<PurchaseOrder> consumer) {
		repo.findById(id).ifPresent(consumer.andThen(this::updateOrder));
	}

	public void updateOrder(PurchaseOrder order) {

		PaymentStatus paymentStatus = order.getPaymentStatus();

		boolean isPaymentSucc = PaymentStatus.PAYMENT_COMPLETED.equals(paymentStatus);

		OrderStatus status = isPaymentSucc ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_FAILED;

		order.setOrderStatus(status);

		if (isPaymentSucc) {
			publisher.publishOrderEvent(convertEntityToDto(order), status);
		}
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
