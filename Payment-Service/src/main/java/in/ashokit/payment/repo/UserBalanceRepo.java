package in.ashokit.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.payment.entity.UserBalanceEntity;

public interface UserBalanceRepo extends JpaRepository<UserBalanceEntity, Integer>{

}
