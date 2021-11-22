package qc.ca.claurendeau.belkinandrei.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import qc.ca.claurendeau.belkinandrei.dto.StudentCreationDTO;
import qc.ca.claurendeau.belkinandrei.entity.Student;
import qc.ca.claurendeau.belkinandrei.persistence.StudentRepository;
import qc.ca.claurendeau.belkinandrei.util.CryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class StudentService {
    @Inject
    StudentRepository studentRepository;

    @Transactional
    public Student createStudent(StudentCreationDTO dto) {
        if (this.getStudentByEmail(dto.getEmail()).isPresent())
            return null;

        Student student = Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .studentId(dto.getStudentId())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .resumes(new ArrayList<>())
                .password(BcryptUtil.bcryptHash(dto.getPassword(), CryptUtil.ITERATIONS, CryptUtil.SALT))
                .build();
        studentRepository.persistAndFlush(student);
        return student;
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.listAll();
    }

    @Transactional
    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findByIdOptional(id);
    }

    @Transactional
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.find("email", email).firstResultOptional();
    }
}
