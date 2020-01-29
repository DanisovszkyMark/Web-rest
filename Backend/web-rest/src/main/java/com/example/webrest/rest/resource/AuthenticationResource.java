package com.example.webrest.rest.resource;

import com.example.webrest.rest.classes.EmailSender;
import com.example.webrest.rest.database.AuthenticationManager;
import com.example.webrest.rest.pojo.LoginRequest;
import com.example.webrest.rest.pojo.RegistrationRequest;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationResource {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private EmailSender emailSender;

    @POST
    @Path("/reg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registration(RegistrationRequest registrationRequest){
        String activationKey = this.authenticationManager.registration(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail());

        if(activationKey != null){
            new Thread(() -> {
                try {
                    emailSender.SendMail(registrationRequest.getEmail(), "Fiók aktiválása", registrationRequest.getUsername(), "Fiókodat az alábbi linkre kattintva aktiválhatod: http://localhost:4200/auth/activation/" + activationKey);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            return Response.status(201).build();
        }

        return Response.status(409).build();
    }// 201, 409, 500

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(LoginRequest loginRequest){

        String token = this.authenticationManager.login(loginRequest.getUsername(), loginRequest.getPassword());

        if(token.length() == 32){
            return token;
        }
        else{
            throw new NotFoundException();
        }
    }// 201, 404, 500

    @DELETE
    @Path("/logout")
    public Response logout(@HeaderParam("token") String token){
        if(this.authenticationManager.logout(token)){
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }// 200, 404, 500, 503

    @PUT
    @Path("/activation/{activationKey}")
    public Response activateUser(@PathParam("activationKey") String activationKey){
        if(this.authenticationManager.activateUser(activationKey)){
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }// 200, 404, 500

    @PUT
    @Path("/block/{id}")
    public Response block(@PathParam("id") long id){
        if(this.authenticationManager.blockUser(id)){
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }//200, 404, 500, 503
}
