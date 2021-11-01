package qc.ca.claurendeau.belkinandrei.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import qc.ca.claurendeau.belkinandrei.entity.Resume;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ResumeRepository implements PanacheRepositoryBase<Resume, UUID> {
}
