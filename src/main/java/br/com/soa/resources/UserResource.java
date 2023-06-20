package br.com.soa.resources;

import br.com.soa.dto.UserDTO;
import br.com.soa.services.UserService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "UserResource", description = "Endpoints relacionados a operações com a entidade Aluno.")
public class UserResource {

    @Inject
    UserService service;

    @GET
//    @RolesAllowed("admin")
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @POST
    public Response saveUser(@RequestBody UserDTO userDTO){
        return Response.status(Response.Status.CREATED).entity(service.saveUser(userDTO)).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateUser(@RequestBody UserDTO userDTO, @PathParam("userId") Integer userId){
        return Response.status(Response.Status.CREATED).entity(service.updateUser(userDTO, userId)).build();
    }
}
