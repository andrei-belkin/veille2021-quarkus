package qc.ca.claurendeau.belkinandrei.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import lombok.Builder.Default;
import qc.ca.claurendeau.belkinandrei.util.ReviewState;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@Entity
public class Resume extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    @Lob
    private String file;
    @Default
    private ReviewState reviewState = ReviewState.PENDING;
    private String reasonForRejection;

    @JsonIgnoreProperties("resumes")
    @ManyToOne(targetEntity = Student.class, optional = false)
    private Student owner;
}
