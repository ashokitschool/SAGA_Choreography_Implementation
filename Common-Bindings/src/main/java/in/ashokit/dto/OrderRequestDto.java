package in.ashokit.dto;

import lombok.Data;

@Data
public class OrderRequestDto {

	private Integer userId;
	private Integer productId;
	private Double amount;
	private Integer orderId;
	private String orderStatus;

	public OrderRequestDto() {
	}

	public OrderRequestDto(Integer userId, Integer productId, Double amount, Integer orderId, String orderStatus) {
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
