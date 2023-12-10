package in.ashokit.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTransaction {

	@Id
	private Integer orderId;

	private Integer userId;

	private Double amount;

	public UserTransaction() {
		// TODO Auto-generated constructor stub
	}

	public UserTransaction(Integer orderId, Integer userId, Double amount) {
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserTransaction [orderId=" + orderId + ", userId=" + userId + ", amount=" + amount + "]";
	}

}
