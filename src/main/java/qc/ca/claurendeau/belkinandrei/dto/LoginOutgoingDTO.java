package qc.ca.claurendeau.belkinandrei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class LoginOutgoingDTO {
    private UUID id;
    private String studentId;
    private String email;
    private String role;
}
