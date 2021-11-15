package qc.ca.claurendeau.belkinandrei.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@Entity
public class Student extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String firstName;
    private String lastName;
    private String address;
    private String studentId;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private List<Resume> resumes;
}
