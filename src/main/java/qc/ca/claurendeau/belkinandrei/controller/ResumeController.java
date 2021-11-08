package qc.ca.claurendeau.belkinandrei.controller;

import com.google.gson.Gson;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import qc.ca.claurendeau.belkinandrei.dto.ResumeCreationDTO;
import qc.ca.claurendeau.belkinandrei.entity.Resume;
import qc.ca.claurendeau.belkinandrei.service.ResumeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/api/resume")
public class ResumeController {
    private final Gson gson = new Gson();
    @Inject
    ResumeService resumeService;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject createResume(JsonObject resumeCreationDtoJson) {
        ResumeCreationDTO resumeCreationDTO = gson.fromJson(String.valueOf(resumeCreationDtoJson), ResumeCreationDTO.class);
        Resume resume = resumeService.createResume(resumeCreationDTO);
        return JsonObject.mapFrom(resume);
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAllResumes() {
        List<Resume> resumes = new ArrayList<>(resumeService.getAllResumes());
        return new JsonArray(Json.encode(resumes));
    }

    @GET
    @Path("/get/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getPendingResumes() {
        List<Resume> resumes = new ArrayList<>(resumeService.getResumesWithPendingApproval());
        JsonArray resumesJson = new JsonArray(resumes);
        System.out.println("Controller : " + resumesJson.encodePrettily());
        return resumesJson;
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getResumeById(@PathParam("id") UUID id) {
        Optional<Resume> resumeOpt = resumeService.getResumeById(id);
        return JsonObject.mapFrom(resumeOpt.orElse(null));
    }

    @GET
    @Path("/get/byOwner/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getResumesByOwnerId(@PathParam("ownerId") UUID ownerId) {
        List<Resume> resumes = new ArrayList<>(resumeService.getResumesByOwnerId(ownerId));
        return new JsonArray(resumes);
    }
}
