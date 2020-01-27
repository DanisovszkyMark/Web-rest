package com.example.webrest.rest.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationResource {

    @POST
    @Path("/reg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registration(){
        return Response.status(404).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(){
        return Response.status(404).build();
    }

    @DELETE
    @Path("/logout")
    public Response logout(@HeaderParam("token") String token){
        return Response.status(404).build();
    }

    @PUT
    @Path("/activation/{activationKey}")
    public Response activateUser(@PathParam("activationKey") String activationKey){
        return Response.status(404).build();
    }

    @PUT
    @Path("/block/{id}")
    public Response block(@PathParam("id") long id){
        return Response.status(404).build();
    }
}
