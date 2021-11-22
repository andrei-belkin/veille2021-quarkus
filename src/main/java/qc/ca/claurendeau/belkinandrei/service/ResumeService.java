package qc.ca.claurendeau.belkinandrei.service;

import qc.ca.claurendeau.belkinandrei.dto.ResumeCreationDTO;
import qc.ca.claurendeau.belkinandrei.entity.Resume;
import qc.ca.claurendeau.belkinandrei.entity.Student;
import qc.ca.claurendeau.belkinandrei.persistence.ResumeRepository;
import qc.ca.claurendeau.belkinandrei.util.ReviewState;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ResumeService {
    @Inject
    ResumeRepository resumeRepository;

    @Inject
    StudentService studentService;

    @Transactional
    public Resume createResume(ResumeCreationDTO dto) {
        Resume resume = Resume.builder()
                .name(dto.getName())
                .file(dto.getFile())
                .ownerId(dto.getOwnerId())
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
    public List<Resume> getResumesByOwnerId(UUID ownerId) {
        Optional<Student> studentOpt = studentService.getStudentById(ownerId);
        if (studentOpt.isEmpty())
            return new ArrayList<>();
        Student student = studentOpt.get();
        return resumeRepository.find("owner", student).stream().collect(Collectors.toList());
    }
}
