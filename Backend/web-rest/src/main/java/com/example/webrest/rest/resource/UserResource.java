package com.example.webrest.rest.resource;

import com.example.webrest.rest.database.UsersManager;
import com.example.webrest.rest.dto.UserDTO;
import com.example.webrest.rest.entity.User;
import com.example.webrest.rest.pojo.UpdateRequest;
import com.example.webrest.rest.security.AuthenticationRequired;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UsersManager usersManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @AuthenticationRequired
    public List<UserDTO> getUsers() {

        List<UserDTO> userDTOs = new ArrayList<>();
        List<User> users;

        try{
            users = this.usersManager.getUsers();
        }
        catch (NoResultException ex){
            throw new NotFoundException(ex);
        }

        for(User user : users){
            userDTOs.add(new UserDTO(user));
        }

        return userDTOs;
    } //Result: 200, 404, 500, 503

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @AuthenticationRequired
    public UserDTO getUser(@PathParam("id") long id){
        User user;

        try {
            user = this.usersManager.getUser(id);
        }
        catch (NoResultException ex){
            throw new NotFoundException(ex);
        }

        return new UserDTO(user);
    }//result: 200, 404, 500, 503

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON) //415 ha unsupported media type
    @AuthenticationRequired
    public Response updateUser(@PathParam("id") long id, UpdateRequest updateRequest){
        if (this.usersManager.updateUser(id, updateRequest.getUsername(), updateRequest.getPassword(), updateRequest.getEmail())) {
            return Response.ok(200).build();
        }

        return Response.status(409).build();
    }//200, 409, 500, 503  (ha az id rossz akkor is 409 a válasz, ezt illik javítani később)

    @DELETE
    @Path("/{id}")
    @AuthenticationRequired
    public Response deleteUser(@PathParam("id") long id){
        if(this.usersManager.deleteUser(id)){
            return Response.ok(200).build();
        }

        return Response.status(404).build();
    } //200, 404, 500, 503
}
