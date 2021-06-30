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
@Table(name = "CSMB_CLIENT")
public class Client {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
