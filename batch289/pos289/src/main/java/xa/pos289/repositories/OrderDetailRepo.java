package xa.pos289.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xa.pos289.models.OrderDetail;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

	@Query(value="FROM OrderDetail WHERE OrderHeaderId = ?1")
	List<OrderDetail> getDetailByHeaderId(Long hid);
}
