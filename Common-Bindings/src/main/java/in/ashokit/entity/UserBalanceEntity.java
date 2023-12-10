package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserBalanceEntity {

	@Id
	private Integer userId;

	private Double amount;

	public UserBalanceEntity() {
	}

	public UserBalanceEntity(Integer userId, Double amount) {
		super();
		this.userId = userId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserBalanceEntity [userId=" + userId + ", amount=" + amount + "]";
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

}
