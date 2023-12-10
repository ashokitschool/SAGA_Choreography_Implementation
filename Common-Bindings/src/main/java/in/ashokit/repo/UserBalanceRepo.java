package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.UserBalanceEntity;

public interface UserBalanceRepo extends JpaRepository<UserBalanceEntity, Integer>{

}
