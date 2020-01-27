package com.example.webrest.rest.database;

import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

public class AuthenticationManager extends BaseDatabaseManager {

    @Transactional
    public boolean registration(String username, String password, String email){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("registration");
        query.setParameter("p_username", username);
        query.setParameter("p_password", password);
        query.setParameter("p_email", email);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }

    @Transactional
    public String login(String username, String password){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("login");
        query.setParameter("p_username", username);
        query.setParameter("p_password", password);
        query.execute();

        return (String)query.getOutputParameterValue("token");
    }

    @Transactional
    public boolean logout(String tokenKey){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("logout");
        query.setParameter("p_tokenKey", tokenKey);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }

    @Transactional
    public boolean activateUser(String activationKey){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("activateUser");
        query.setParameter("p_activationKey", activationKey);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }

    @Transactional
    public boolean blockUser(long id){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("blockUser");
        query.setParameter("p_id", id);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }
}
