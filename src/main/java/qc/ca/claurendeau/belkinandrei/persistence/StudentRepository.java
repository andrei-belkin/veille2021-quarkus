package qc.ca.claurendeau.belkinandrei.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import qc.ca.claurendeau.belkinandrei.entity.Student;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, UUID> {
}
