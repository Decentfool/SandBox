package su.csmb.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseClients {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
