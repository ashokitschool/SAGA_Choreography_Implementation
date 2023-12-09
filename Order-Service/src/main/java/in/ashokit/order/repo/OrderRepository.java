package in.ashokit.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.order.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer>{

}
