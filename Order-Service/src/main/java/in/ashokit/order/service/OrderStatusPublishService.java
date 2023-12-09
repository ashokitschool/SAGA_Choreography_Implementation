package in.ashokit.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.OrderStatus;
import in.ashokit.event.OrderEvent;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublishService {

	@Autowired
	private Sinks.Many<OrderEvent> orderSinks;

	public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
		OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
		orderSinks.tryEmitNext(orderEvent);// publish event to topic
	}
}
