package qc.ca.claurendeau.belkinandrei.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder(toBuilder = true)
@UserDefinition
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

    @Username
    @Column(unique = true)
    private String email;

    @Password
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Roles
    private String role;
}
