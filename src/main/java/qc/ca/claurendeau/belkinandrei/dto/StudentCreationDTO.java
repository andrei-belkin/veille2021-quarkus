package qc.ca.claurendeau.belkinandrei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentCreationDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String studentId;
    private String phoneNumber;
    private String email;
    private String password;
}
