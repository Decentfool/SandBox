package su.csmb.server.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.csmb.server.data.entity.Client;
@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Long> {
    Client getClientInfoByPhoneNumber(String phoneNumber);
}
