package su.csmb.server.data.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import su.csmb.common.response.ResponseBalance;
import su.csmb.common.response.ResponseCards;
import su.csmb.common.response.ResponseClients;
import su.csmb.server.data.services.SrvGetBalanceByCardIdServer;
import su.csmb.server.data.objects.CardObject;
import su.csmb.server.data.services.SrvGetCardsByClientIdServer;
import su.csmb.server.data.objects.ClientObject;
import su.csmb.server.data.services.SrvGetClientInfoByPhoneServer;

import java.util.List;

@RestController
@AllArgsConstructor
@Log

public class RestControllerServer {
    private SrvGetClientInfoByPhoneServer clientInfoByPhone;
    private SrvGetCardsByClientIdServer cardsByClientId;
    private SrvGetBalanceByCardIdServer balanceCardId;

    @GetMapping("/healthcheck")
    public String getAvailable() {
        return "{\"response\": [ {\"code\": 0, \"description\": \"Server Available\"}]}";
    }

    @PostMapping("/getClientInfoByPhone")
    public ResponseClients getClientById(@RequestParam String phoneNumber) {
        return clientInfoByPhone.getClientInfoByPhone(phoneNumber);
    }
    @PostMapping("/getCardsByClientId")
    public List<ResponseCards> getCardsByClientId(@RequestParam Long ClientId) {
        return cardsByClientId.getCardsByClientId(ClientId);
    }
    @PostMapping("/getBalanceByCardId")
    public ResponseBalance getBalanceByCardId(@RequestParam Long cardId) {
        return balanceCardId.getBalanceByCardId(cardId);
    }
}
