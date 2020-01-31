package com.example.webrest.rest.database;

import com.example.webrest.rest.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsersManager extends BaseDatabaseManager {

    public List<User> getUsers(){
        return this.getEntityManager().createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUser(long id){
        return this.getEntityManager().createQuery("SELECT u FROM User u WHERE id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Transactional
    public boolean updateUser(long id, String newUsername, String newPassword, String newEmail){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("updateUser");
        query.setParameter("p_id", id);
        query.setParameter("p_username", newUsername);
        query.setParameter("p_password", newPassword);
        query.setParameter("p_email", newEmail);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }

    @Transactional
    public Boolean updateUserWithoutPassword(long id, String username, String email){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("updateUserWithoutPassword");
        query.setParameter("p_id", id);
        query.setParameter("p_username", username);
        query.setParameter("p_email", email);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }

    @Transactional
    public boolean deleteUser(long id){
        StoredProcedureQuery query = this.getEntityManager().createNamedStoredProcedureQuery("deleteUser");
        query.setParameter("p_id", id);
        query.execute();

        return (Boolean)query.getOutputParameterValue("success");
    }
}
