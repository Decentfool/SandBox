package su.csmb.client.data.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su.csmb.common.request.*;

@Service
@AllArgsConstructor
public class SrvGetClientInfoByPhoneClient {

    public String getClientInfoByPhone(String phoneNumber) {
        RestTemplate template = new RestTemplate();
        String url = "http://127.0.0.1:8080/getClientInfoByPhone?phoneNumber=";
        HttpEntity<RequestClients> request = new HttpEntity<>(new RequestClients(phoneNumber));
        ResponseEntity<String> response = template.postForEntity(url + phoneNumber, request, String.class);
        return response.getBody();
    }

}
