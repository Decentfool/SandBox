package su.csmb.server.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.csmb.server.data.entity.Cards;

import java.util.List;
@Repository
public interface CardsCrudRepository extends CrudRepository<Cards, Long> {

    List<Cards> findByClientId(Long clientId);
}
