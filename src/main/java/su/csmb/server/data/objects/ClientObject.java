package su.csmb.server.data.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ClientObject {
    private Long Id;
    private String FirstName;
    private String LastName;
    private String phoneNumber;
    private String email;

}
