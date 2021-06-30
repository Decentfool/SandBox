package su.csmb.server.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.csmb.server.data.entity.Balance;

@Repository
public interface BalanceCrudRepository extends CrudRepository<Balance, Long> {
    Balance findByCardId(Long CardId);
}
