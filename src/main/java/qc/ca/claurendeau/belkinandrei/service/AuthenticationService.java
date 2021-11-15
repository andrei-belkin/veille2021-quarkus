package qc.ca.claurendeau.belkinandrei.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import qc.ca.claurendeau.belkinandrei.dto.LoginIncomingDTO;
import qc.ca.claurendeau.belkinandrei.dto.LoginOutgoingDTO;
import qc.ca.claurendeau.belkinandrei.entity.Student;
import qc.ca.claurendeau.belkinandrei.util.CryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@ApplicationScoped
public class AuthenticationService {
    @Inject
    StudentService studentService;

    @Transactional
    public LoginOutgoingDTO authenticate(LoginIncomingDTO dto) {
        Optional<Student> studentOpt = studentService.getStudentByEmail(dto.getEmail());
        if (studentOpt.isEmpty() || BcryptUtil.bcryptHash(
                studentOpt.get().getPassword(),
                CryptUtil.ITERATIONS,
                dto.getPassword().getBytes(StandardCharsets.UTF_8)
        ).equals(dto.getPassword()))
            return null;
        Student student = studentOpt.get();
        return new LoginOutgoingDTO(
                student.getId(),
                student.getStudentId(),
                student.getEmail(),
                student.getRole()
        );
    }
}
