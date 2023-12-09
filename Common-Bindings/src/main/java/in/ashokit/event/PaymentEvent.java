package in.ashokit.event;

import java.util.Date;
import java.util.UUID;

import in.ashokit.dto.PaymentRequestDto;
import in.ashokit.dto.PaymentStatus;

public class PaymentEvent implements Event {

	private UUID eventId = UUID.randomUUID();
	private Date eventDate = new Date();
	private PaymentRequestDto paymentRequestDto;
	private PaymentStatus paymentStatus;

	@Override
	public UUID getEventId() {
		return eventId;
	}

	@Override
	public Date getDate() {
		return eventDate;
	}

	public PaymentEvent(PaymentRequestDto requestDto, PaymentStatus status) {
		this.paymentRequestDto = requestDto;
		this.paymentStatus = status;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public PaymentRequestDto getPaymentRequestDto() {
		return paymentRequestDto;
	}

	public void setPaymentRequestDto(PaymentRequestDto paymentRequestDto) {
		this.paymentRequestDto = paymentRequestDto;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

}
