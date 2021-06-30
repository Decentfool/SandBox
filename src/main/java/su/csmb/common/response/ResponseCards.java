package su.csmb.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseCards {
    private Long id;
    private Long tariff;
    private String cardNumber;
}
