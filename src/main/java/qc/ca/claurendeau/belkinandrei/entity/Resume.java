package qc.ca.claurendeau.belkinandrei.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import lombok.Builder.Default;
import qc.ca.claurendeau.belkinandrei.util.ReviewState;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
public class Resume extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    @Lob
    private String file;
    @Default
    private ReviewState reviewState = ReviewState.PENDING;
    private String reasonForRejection;
    private UUID ownerId;
}
