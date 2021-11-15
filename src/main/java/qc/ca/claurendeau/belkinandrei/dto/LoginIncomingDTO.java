package qc.ca.claurendeau.belkinandrei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginIncomingDTO {
    private String email;
    private String password;
}
