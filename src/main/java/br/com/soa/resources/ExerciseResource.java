package br.com.soa.resources;

import br.com.soa.dto.ExerciseDTO;
import br.com.soa.services.ExerciseService;
import br.com.soa.services.ExerciseService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/exercises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "ExerciseResource", description = "Endpoints relacionados a operações com a entidade Exercise.")
//@Authenticated
public class ExerciseResource {

    @Inject
    ExerciseService service;

    @GET
//    @RolesAllowed("admin")
    public Response listAll() {
        return Response.ok(service.listAll()).build();
    }

    @GET
    @Path("/{exerciseId}")
    public Response getExerciseById(@PathParam("exerciseId") Integer exerciseId) {
        return Response.ok(service.getExerciseById(exerciseId)).build();
    }

    @POST
    public Response saveExercise(@RequestBody ExerciseDTO exerciseDTO) {
        try {
            return Response.status(Response.Status.CREATED).entity(service.saveExercise(exerciseDTO)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("/{exerciseId}")
    public Response updateExercise(@RequestBody ExerciseDTO exerciseDTO, @PathParam("exerciseId") Integer exerciseId) {
        try {
            return Response.status(Response.Status.OK).entity(service.updateExercise(exerciseDTO, exerciseId)).build();
        } catch (NotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{exerciseId}")
    public Response deleteExercise(@PathParam("exerciseId") Integer exerciseId) {
        service.deleteExercise(exerciseId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
