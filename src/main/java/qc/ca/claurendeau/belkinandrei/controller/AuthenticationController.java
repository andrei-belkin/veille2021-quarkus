package qc.ca.claurendeau.belkinandrei.controller;

import io.vertx.core.json.JsonObject;
import qc.ca.claurendeau.belkinandrei.dto.LoginIncomingDTO;
import qc.ca.claurendeau.belkinandrei.dto.LoginOutgoingDTO;
import qc.ca.claurendeau.belkinandrei.service.AuthenticationService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/auth")
public class AuthenticationController {
    @Inject
    AuthenticationService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Context SecurityContext securityContext, [as parameter]
    public JsonObject login(JsonObject inDtoJson) {
//        return JsonObject.mapFrom(securityContext.getUserPrincipal().getName());
        System.out.println(inDtoJson.encode());
        LoginIncomingDTO inDto = inDtoJson.mapTo(LoginIncomingDTO.class);
        System.out.println(inDto.toString());
        LoginOutgoingDTO outDto = service.authenticate(inDto);
        return JsonObject.mapFrom(outDto);
    }
}
