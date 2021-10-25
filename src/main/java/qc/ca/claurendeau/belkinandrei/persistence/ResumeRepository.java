package qc.ca.claurendeau.belkinandrei.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import qc.ca.claurendeau.belkinandrei.entity.Resume;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResumeRepository implements PanacheRepository<Resume> {
}
