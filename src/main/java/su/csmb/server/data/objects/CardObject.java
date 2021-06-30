package su.csmb.server.data.objects;

import lombok.Value;

@Value
public class CardObject {
    Long id;
    Long clientId;
    Long tariff;
    String cardNumber;
}
