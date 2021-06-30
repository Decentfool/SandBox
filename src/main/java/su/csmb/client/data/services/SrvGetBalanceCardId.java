package su.csmb.client.data.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su.csmb.common.request.*;

@Service
@AllArgsConstructor
public class SrvGetBalanceCardId {

    public String getBalanceCardId(Long cardId) {
        RestTemplate template = new RestTemplate();
        String url = "http://127.0.0.1:8080/getBalanceByCardId?cardId=";
        HttpEntity<RequestBalance> request = new HttpEntity<>(new RequestBalance(cardId));
        ResponseEntity<String> response = template.postForEntity(url + cardId, request, String.class);
        return response.getBody();
    }

}
