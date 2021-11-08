package qc.ca.claurendeau.belkinandrei.service;

import qc.ca.claurendeau.belkinandrei.dto.ResumeCreationDTO;
import qc.ca.claurendeau.belkinandrei.entity.Resume;
import qc.ca.claurendeau.belkinandrei.persistence.ResumeRepository;
import qc.ca.claurendeau.belkinandrei.util.ReviewState;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResumeService {
    @Inject
    ResumeRepository resumeRepository;

    @Transactional
    public Resume createResume(ResumeCreationDTO resumeDto) {
        Resume resume = Resume.builder()
                .name(resumeDto.getName())
                .file(resumeDto.getFile())
                .ownerId(resumeDto.getOwnerId())
                .build();
        resumeRepository.persistAndFlush(resume);
        return resume;
    }

    @Transactional
    public List<Resume> getAllResumes() {
        return resumeRepository.listAll();
    }

    @Transactional
    public List<Resume> getResumesWithPendingApproval() {
        return resumeRepository.find("reviewState", ReviewState.PENDING).stream().collect(Collectors.toList());
    }

    @Transactional
    public Optional<Resume> getResumeById(UUID id) {
        return resumeRepository.findByIdOptional(id);
    }

    @Transactional
    public List<Resume> getResumesByOwnerId(UUID id) {
        return resumeRepository.find("ownerId", id).stream().collect(Collectors.toList());
    }
}
