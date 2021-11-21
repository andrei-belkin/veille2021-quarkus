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
        System.out.println("### Started auth");

        Optional<Student> studentOpt = studentService.getStudentByEmail(dto.getEmail());
        System.out.println("### Got Optional : " + studentOpt.toString());

        if (studentOpt.isEmpty()) {
            System.out.println("### Optional is empty, returning null");
            return null;
        }

        System.out.println("### Password is : " + dto.getPassword());
        String cryptedPass = BcryptUtil.bcryptHash(
                dto.getPassword(),
                CryptUtil.ITERATIONS,
                CryptUtil.SALT
        );
        System.out.println("### Encrypted password is : " + cryptedPass);

        if (!cryptedPass.equals(studentOpt.get().getPassword())) {
            System.out.println("### Passwords do not match");
            return null;
        }

        System.out.println("### Passwords match");
        Student student = studentOpt.get();
        System.out.println("### Student : " + student);

        return new LoginOutgoingDTO(
                student.getId(),
                student.getStudentId(),
                student.getEmail(),
                student.getRole()
        );
    }
}
