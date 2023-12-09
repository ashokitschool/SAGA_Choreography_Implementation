package in.ashokit.order.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import in.ashokit.event.PaymentEvent;

@Configuration
public class EventConsumerConfig {

	@Autowired
	private OrderStatusUpdateHandler handler;

	@Bean
	public Consumer<PaymentEvent> paymentEventConsumer() {

		// listen to payment-event topic
		// check payment status
		// if payment success - confirm the order
		// if payment failed - cancel order

		return (payment) -> handler.updateOrder(payment.getPaymentRequestDto().getOrderId(),
				po -> po.setPaymentStatus(payment.getPaymentStatus()));
	}

}
