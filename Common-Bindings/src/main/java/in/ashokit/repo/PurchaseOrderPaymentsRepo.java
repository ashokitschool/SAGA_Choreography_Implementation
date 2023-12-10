package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.PurchaseOrderPayments;

public interface PurchaseOrderPaymentsRepo extends JpaRepository<PurchaseOrderPayments, Integer>{

}
