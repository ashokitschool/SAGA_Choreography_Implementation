package in.ashokit.dto;

public class OrderRequestDto {

	private Integer userId;
	private Integer productId;
	private Double amount;
	private Integer orderId;

	public OrderRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderRequestDto(Integer userId, Integer productId, Double amount, Integer orderId) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.orderId = orderId;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderRequestDto [userId=" + userId + ", productId=" + productId + ", amount=" + amount + ", orderId="
				+ orderId + "]";
	}

}
