package in.ashokit.payment.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.OrderRequestDto;
import in.ashokit.dto.PaymentRequestDto;
import in.ashokit.dto.PaymentStatus;
import in.ashokit.event.OrderEvent;
import in.ashokit.event.PaymentEvent;
import in.ashokit.payment.entity.UserBalanceEntity;
import in.ashokit.payment.entity.UserTransaction;
import in.ashokit.payment.repo.UserBalanceRepo;
import in.ashokit.payment.repo.UserTransactionRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {

	@Autowired
	private UserBalanceRepo userBalanceRepo;

	@Autowired
	private UserTransactionRepo userTxRepo;

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
	public PaymentEvent newOrderEvent(OrderEvent orderEvent) {

		OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();

		PaymentRequestDto paymentReqDto = new PaymentRequestDto(orderRequestDto.getOrderId(),
				orderRequestDto.getUserId(), orderRequestDto.getAmount());

		return userBalanceRepo.findById(orderRequestDto.getUserId())
				.filter(ub -> ub.getAmount() > orderRequestDto.getAmount())
				.map(ub -> {
					ub.setAmount(ub.getAmount() - orderRequestDto.getAmount());
					userTxRepo.save(new UserTransaction(orderRequestDto.getOrderId(), 
							orderRequestDto.getUserId(),
							orderRequestDto.getAmount())
					);
					return new PaymentEvent(paymentReqDto, PaymentStatus.PAYMENT_COMPLETED);
				}).orElse(new PaymentEvent(paymentReqDto, PaymentStatus.PAYMENT_FAILED));

	}
	
	
	public void cancelOrderEvent(OrderEvent orderEvent) {
		userTxRepo.findById(orderEvent.getOrderRequestDto().getOrderId())
				  .ifPresent(ut -> {
					  userTxRepo.delete(ut);
					  
					  userTxRepo.findById(ut.getUserId())
					  			.ifPresent(ub -> ub.setAmount(ub.getAmount() + ut.getAmount()));
				  });
	}

}
