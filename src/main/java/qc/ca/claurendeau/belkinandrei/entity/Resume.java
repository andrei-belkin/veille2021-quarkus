package qc.ca.claurendeau.belkinandrei.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import lombok.Builder.Default;
import qc.ca.claurendeau.belkinandrei.util.ReviewState;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Resume {
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
//    private List<UUID> studentApplications;
}
