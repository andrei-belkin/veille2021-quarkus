package qc.ca.claurendeau.belkinandrei.controller;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import qc.ca.claurendeau.belkinandrei.dto.ResumeCreationDTO;
import qc.ca.claurendeau.belkinandrei.dto.ResumeDeletionResultDTO;
import qc.ca.claurendeau.belkinandrei.entity.Resume;
import qc.ca.claurendeau.belkinandrei.service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/api/resumes")
public class ResumeController {
    @Inject
    ResumeService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject createResume(JsonObject dtoJson) {
        ResumeCreationDTO dto = dtoJson.mapTo(ResumeCreationDTO.class);
        Resume resume = service.createResume(dto);
        return JsonObject.mapFrom(resume);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAllResumes() {
        List<Resume> resumes = new ArrayList<>(service.getAllResumes());
        return new JsonArray(Json.encode(resumes));
    }

    @GET
    @Path("/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getPendingResumes() {
        List<Resume> resumes = new ArrayList<>(service.getResumesWithPendingApproval());
        return new JsonArray(resumes);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResumeById(@PathParam("id") UUID id) {
        Optional<Resume> resumeOpt = service.getResumeById(id);
        return JsonObject.mapFrom(resumeOpt.orElse(null));
    }

    @GET
    @Path("/owner/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getResumesByOwnerId(@PathParam("ownerId") UUID ownerId) {
        List<Resume> resumes = new ArrayList<>(service.getResumesByOwnerId(ownerId));
        return new JsonArray(resumes);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject deleteResumeById(@PathParam("id") UUID id) {
        ResumeDeletionResultDTO deletionResult = service.deleteResumeById(id);
        return JsonObject.mapFrom(deletionResult);
    }
}
