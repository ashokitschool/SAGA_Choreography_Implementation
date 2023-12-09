package in.ashokit.event;

import java.util.Date;
import java.util.UUID;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.OrderStatus;

public class OrderEvent implements Event {

	private UUID eventId = UUID.randomUUID();
	private Date eventDate = new Date();
	private OrderRequestDto orderRequestDto;
	private OrderStatus orderStatus;

	public Date getDate() {
		return eventDate;
	}

	public UUID getEventId() {
		return eventId;
	}

	public OrderEvent(OrderRequestDto requestDto, OrderStatus orderStatus) {
		this.orderRequestDto = requestDto;
		this.orderStatus = orderStatus;
	}

	public OrderRequestDto getOrderRequestDto() {
		return orderRequestDto;
	}

	public void setOrderRequestDto(OrderRequestDto orderRequestDto) {
		this.orderRequestDto = orderRequestDto;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


}
