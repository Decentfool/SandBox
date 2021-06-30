package su.csmb.client.data.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import su.csmb.client.data.services.SrvGetBalanceCardId;
import su.csmb.client.data.services.SrvGetCardsByClientIdClient;
import su.csmb.client.data.services.SrvGetClientInfoByPhoneClient;

@RestController
@AllArgsConstructor
@Log
public class RestControllerClient {
    private SrvGetClientInfoByPhoneClient clientService;
    private SrvGetCardsByClientIdClient cardsService;
    private SrvGetBalanceCardId balanceCardService;

    @GetMapping("/getClientInfoByPhone")
    public String getClientInfoByPhone(@RequestParam String phoneNumber) {
        return clientService.getClientInfoByPhone(phoneNumber);
    }
    @GetMapping("/getCardsByClientId")
    public String getCardsByClientId(@RequestParam Long ClientId) {
        return cardsService.getCardsByClientId(ClientId);
    }

    @GetMapping("/getBalanceByCard")
    public String getBalanceByCard(@RequestParam Long cardId) {
        return balanceCardService.getBalanceCardId(cardId);
    }
}
