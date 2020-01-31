package com.example.webrest.rest.security;

import com.example.webrest.rest.database.AuthenticationManager;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    public static final String TOKEN_KEY = "token";

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private AuthenticationManager authenticationManager;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        Method method = this.resourceInfo.getResourceMethod();

        if(method.isAnnotationPresent(AuthenticationRequired.class)){
            final MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();

            if(!headers.containsKey(TOKEN_KEY)){
                containerRequestContext.abortWith(Response.status(401)
                        .entity("Token not found with key: " + TOKEN_KEY + "!").build());

            }
            else if(!authenticationManager.tokenIsValid(headers.getFirst(TOKEN_KEY))){
                containerRequestContext.abortWith(Response.status(401)
                        .entity("Invalid Token!").build());
            }
        }
    }

}