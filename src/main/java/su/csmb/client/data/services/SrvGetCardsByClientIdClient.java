package su.csmb.client.data.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su.csmb.common.request.*;

@Service
public class SrvGetCardsByClientIdClient {

    public String getCardsByClientId(Long ClientId) {
        RestTemplate template = new RestTemplate();
        String url = "http://127.0.0.1:8080/getCardsByClientId?ClientId=";
        HttpEntity<RequestCards> request = new HttpEntity<>(new RequestCards(ClientId));
        ResponseEntity<String> response = template.postForEntity(url + ClientId, request, String.class);
        return response.getBody();
    }
}
