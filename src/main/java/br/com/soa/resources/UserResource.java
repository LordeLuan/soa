package br.com.soa.resources;

import br.com.soa.dto.UserDTO;
import br.com.soa.services.UserService;
import io.quarkus.security.Authenticated;
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
//@Authenticated
public class UserResource {

    @Inject
    UserService service;

    @GET
//    @RolesAllowed("admin")
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Integer userId) {
        return Response.ok(service.getUserById(userId)).build();
    }

    @POST
    public Response saveUser(@RequestBody UserDTO userDTO) {
        try {
            return Response.status(Response.Status.CREATED).entity(service.saveUser(userDTO)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("/{userId}")
    public Response updateUser(@RequestBody UserDTO userDTO, @PathParam("userId") Integer userId) {
        try {
            return Response.status(Response.Status.OK).entity(service.updateUser(userDTO, userId)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }

    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") Integer userId) {
        service.deleteUser(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
