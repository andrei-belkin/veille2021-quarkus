package qc.ca.claurendeau.belkinandrei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import qc.ca.claurendeau.belkinandrei.entity.Student;

import javax.persistence.Lob;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ResumeCreationDTO {
    private final String name;
    @Lob
    private final String file;
    private final UUID ownerId;
}
