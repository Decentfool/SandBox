package su.csmb.server.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CSMB_CARDS")
public class Cards {
    @Id
    private Long id;
    private Long clientId;
    private String tariff;
    private String cardNumber;

}
