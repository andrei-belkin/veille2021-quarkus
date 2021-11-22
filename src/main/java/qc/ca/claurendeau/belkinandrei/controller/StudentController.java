package qc.ca.claurendeau.belkinandrei.controller;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import qc.ca.claurendeau.belkinandrei.dto.StudentCreationDTO;
import qc.ca.claurendeau.belkinandrei.entity.Student;
import qc.ca.claurendeau.belkinandrei.service.StudentService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/api/students")
public class StudentController {
    @Inject
    StudentService service;

    @POST
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject createStudent(JsonObject dtoJson) {
        StudentCreationDTO dto = dtoJson.mapTo(StudentCreationDTO.class);
        Student student = service.createStudent(dto);
        return JsonObject.mapFrom(student);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAllStudents() {
        List<Student> students = new ArrayList<>(service.getAllStudents());
        return new JsonArray(Json.encode(students));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getStudentById(@PathParam("id") UUID id) {
        Optional<Student> studentOpt = service.getStudentById(id);
        return JsonObject.mapFrom(studentOpt.orElse(null));
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getStudentByEmail(@PathParam("email") String email) {
        Optional<Student> studentOpt = service.getStudentByEmail(email);
        return JsonObject.mapFrom(studentOpt.orElse(null));
    }
}
