package su.csmb.server.data.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.csmb.common.response.ResponseClients;
import su.csmb.server.data.entity.Client;
import su.csmb.server.data.exception.NotFoundException;
import su.csmb.server.data.repository.*;

@Service
@AllArgsConstructor
public class SrvGetClientInfoByPhoneServer {
    private ClientCrudRepository clientCrudRepository;

    public ResponseClients getClientInfoByPhone(String phoneNumber) {
        Client client = clientCrudRepository.getClientInfoByPhoneNumber(phoneNumber);
        if (client == null) {
            throw new NotFoundException("Client not found");
        }
        ResponseClients response = new ResponseClients(client.getId(), client.getFirstName(), client.getLastName(),
                 client.getEmail());
        return response;
    }

}
