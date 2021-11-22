package qc.ca.claurendeau.belkinandrei.controller;

import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import qc.ca.claurendeau.belkinandrei.dto.LoginIncomingDTO;
import qc.ca.claurendeau.belkinandrei.dto.LoginOutgoingDTO;
import qc.ca.claurendeau.belkinandrei.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Slf4j
@Path("/api/auth")
public class AuthenticationController {
    @Inject
    AuthenticationService service;

    @GET
    @Consumes()
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject login(JsonObject inDtoJson) {
        System.out.println("Starting login");
        System.out.println(inDtoJson.encodePrettily());

        System.out.println("Mapping JSON to entity");
        LoginIncomingDTO inDto = inDtoJson.mapTo(LoginIncomingDTO.class);

        System.out.println("Mapped JSON to entity");
        System.out.println(inDto.toString());

        System.out.println("Entering auth service");
        LoginOutgoingDTO outDto = service.authenticate(inDto);

        System.out.println("Returned to controller, authenticated");
        System.out.println(outDto);

        System.out.println("Mapping entity to JSON");
        JsonObject outJson = JsonObject.mapFrom(outDto);

        System.out.println("Mapped entity to JSON");
        System.out.println(outJson);

        System.out.println("Returning JSON");
        System.out.println(LocalDateTime.now());
        return outJson;
    }
}
