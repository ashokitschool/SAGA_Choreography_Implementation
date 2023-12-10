package in.ashokit.dto;

public class OrderResponseDto {

	private Integer userId;
	private Integer productId;
	private Integer amount;
	private Integer orderId;
	private String orderStatus;

	public OrderResponseDto() {
	}

	public OrderResponseDto(Integer userId, Integer productId, Integer amount, Integer orderId, String orderStatus) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.orderId = orderId;
		this.orderStatus = orderStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderResponseDto [userId=" + userId + ", productId=" + productId + ", amount=" + amount + ", orderId="
				+ orderId + ", orderStatus=" + orderStatus + "]";
	}

}
