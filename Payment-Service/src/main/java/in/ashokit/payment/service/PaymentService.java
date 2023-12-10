package in.ashokit.payment.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ashokit.dto.PaymentStatus;
import in.ashokit.entity.PurchaseOrder;
import in.ashokit.entity.PurchaseOrderPayments;
import in.ashokit.entity.UserBalanceEntity;
import in.ashokit.repo.PurchaseOrderPaymentsRepo;
import in.ashokit.repo.UserBalanceRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	private UserBalanceRepo userBalanceRepo;
	
	@Autowired
	private PurchaseOrderPaymentsRepo paymentsRepo;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;


	@PostConstruct
	public void storeUserBalanceInfo() {
		UserBalanceEntity u1 = new UserBalanceEntity(101, 5000.00);
		UserBalanceEntity u2 = new UserBalanceEntity(102, 2000.00);
		UserBalanceEntity u3 = new UserBalanceEntity(103, 4000.00);
		UserBalanceEntity u4 = new UserBalanceEntity(104, 1000.00);
		List<UserBalanceEntity> asList = Arrays.asList(u1, u2, u3, u4);
		userBalanceRepo.saveAll(asList);
	}

	@Transactional
	@KafkaListener(topics = "orders-topic",groupId = "ashokit-group")
	public void handleOrderPayment(PurchaseOrder purchaseOrder) {
		
		Integer orderId = purchaseOrder.getOrderId();
		
		Integer userId = purchaseOrder.getUserId();
		
		userBalanceRepo.findById(userId).ifPresent(ub -> {
			PurchaseOrderPayments payment = new PurchaseOrderPayments();
			payment.setAmount(purchaseOrder.getPrice());
			payment.setOrderId(orderId);
			payment.setUserId(userId);
			payment.setPaymentDate(LocalDate.now());
			
			if(ub.getAmount() > purchaseOrder.getPrice()) {
				ub.setAmount(ub.getAmount() - purchaseOrder.getPrice());
				payment.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED.toString());
				userBalanceRepo.save(ub);
			}else {
				payment.setPaymentStatus(PaymentStatus.PAYMENT_FAILED.toString());
			}
			payment = paymentsRepo.save(payment);
			kafkaTemplate.send("payments-topic", payment);
		});
	}
}
