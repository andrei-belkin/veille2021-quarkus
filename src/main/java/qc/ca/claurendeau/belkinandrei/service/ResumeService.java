package qc.ca.claurendeau.belkinandrei.service;

import qc.ca.claurendeau.belkinandrei.entity.Resume;
import qc.ca.claurendeau.belkinandrei.persistence.ResumeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class ResumeService {
    @Inject
    ResumeRepository resumeRepository;

    @Transactional
    public Resume getResumeById(UUID id) {
        return resumeRepository.findById(id);
    }
}
