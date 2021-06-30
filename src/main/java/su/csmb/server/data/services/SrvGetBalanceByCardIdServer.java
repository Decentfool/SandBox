package su.csmb.server.data.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.csmb.common.response.ResponseBalance;
import su.csmb.server.data.entity.Balance;
import su.csmb.server.data.exception.NotFoundException;
import su.csmb.server.data.repository.*;

@Service
@AllArgsConstructor
public class SrvGetBalanceByCardIdServer {
    private BalanceCrudRepository balanceCrudRepository;

    public ResponseBalance getBalanceByCardId(Long cardId) {
        Balance balance = balanceCrudRepository.findByCardId(cardId);
        if (balance == null) {
            throw new NotFoundException("Balance not found");
        }
        ResponseBalance response = new ResponseBalance(Double.parseDouble(balance.getBalance()));
        return response;
    }

}
