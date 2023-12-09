package in.ashokit.dto;

public class PaymentRequestDto {

	private Integer orderId;
	private Integer userId;
	private Integer amount;

	public PaymentRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public PaymentRequestDto(Integer orderId, Integer userId, Integer amount) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentRequestDto [orderId=" + orderId + ", userId=" + userId + ", amount=" + amount + "]";
	}

}
