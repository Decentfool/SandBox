package su.csmb.server.data.objects;

import lombok.Value;

@Value
public class BalanceObject {
    Long id;
    Long cardId;
    double balance;
}
