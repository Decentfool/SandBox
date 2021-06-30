package su.csmb.server.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CSMB_BALANCE")
public class Balance {
    @Id
    private Long id;
    private Long cardId;
    private String balance;
}
