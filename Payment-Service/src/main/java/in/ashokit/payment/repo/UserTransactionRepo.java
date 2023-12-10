package in.ashokit.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.payment.entity.UserTransaction;

public interface UserTransactionRepo extends JpaRepository<UserTransaction, Integer>{

}
