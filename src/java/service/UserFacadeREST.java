/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.health.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
/**
 *
 * @author hp
 */
@Stateless
@Path("com.health.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "healthPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("findByUserid/{userid}")
    @Produces({"application/json"})
    public List<User> findByUserid(@PathParam("userid") Integer userid) {
        Query query =em.createNamedQuery("User.findByUserid");
        query.setParameter("userid",userid);
        return query.getResultList();
    }
    
    
    @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<User> findByUsername(@PathParam("username") String username) {
        Query query =em.createNamedQuery("User.findByUsername");
        query.setParameter("username",username);
        return query.getResultList();
    }
    
    
    @GET
    @Path("findByUserage/{userage}")
    @Produces({"application/json"})
    public List<User> findByUserage(@PathParam("userage") String userage){
        Query query = em.createNamedQuery("User.findByUserage");
        query.setParameter("userage",userage);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserheight/{userheight}")
    @Produces({"application/json"})
    public List<User> findByUserheight(@PathParam("userheight") String userheight){
        Query query = em.createNamedQuery("User.findByUserheight");
        query.setParameter("userheight", userheight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserweight/{userweight}")
    @Produces({"application/json"})
    public List<User> findByUserweight(@PathParam("userweight") String userweight){
        Query query = em.createNamedQuery("User.findByUserweight");
        query.setParameter("userweight", userweight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUsergender/{usergender}")
    @Produces({"application/json"})
    public List<User> findByUsergender(@PathParam("usergender") String usergender) {
        Query query =em.createNamedQuery("User.findByUsergender");
        query.setParameter("usergender",usergender);
        return query.getResultList();
    }
   
    @GET
    @Path("findByLevelofactivity/{levelOfActivity}")
    @Produces({"application/json"})
    public List<User> findByLevelOfActivity(@PathParam("levelOfActivity") String levelOfActivity) {
        Query query =em.createNamedQuery("User.findByLevelOfActivity");
        query.setParameter("levelOfActivity",levelOfActivity);
        return query.getResultList();
    }

    @GET
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces({"application/json"})
    public List<User> findByStepsPerMile(@PathParam("stepsPerMile") String stepsPerMile){
        Query query = em.createNamedQuery("User.findByStepsPerMile");
        query.setParameter("stepsPerMile",stepsPerMile);
        return query.getResultList();
    }
 
    
    @GET
    @Path("findByPassword/{password}")
    @Produces({"application/json"})
    public List<User> findByPassword(@PathParam("password") String password) {
        Query query =em.createNamedQuery("User.findByPassword");
        query.setParameter("password",password);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUsername/{username}/{password}")
    @Produces({"application/json"})
    public String findByUsername(@PathParam("username") String username,@PathParam("password") String password) {
       try { 
           
        Query query =em.createNamedQuery("User.findByUsername");
        query.setParameter("username",username);
        User u = (User)query.getResultList().get(0);
       // System.out.println(u.getPassword());
        if(password.equals(u.getPassword()))
            return "1";
        else
            return "0";
       }catch (Exception EJBException)
       { return "2";}
    }
    
    @GET
    @Path("SignUp/{username}/{password}/{gender}/{age}/{height}/{weight}/{stepsPerMile}/{level}")
    @Produces({"application/json"})
    public String SignUp (@PathParam("username") String username, @PathParam("password") String password,
            @PathParam("gender") String gender, @PathParam ("age") String age, 
            @PathParam("height") String height, @PathParam("weight") String weight, 
            @PathParam("stepsPerMile") String stepsPerMile, @PathParam("level") String level){
        
        Query query =em.createNamedQuery("User.findByUsername");
        query.setParameter("username",username);
       
        if(query.getResultList().isEmpty()){
            User item = new User(); 
        item.setUsername(username);
        item.setPassword(password);
        item.setUsergender(gender);
        item.setUserage(age);
        item.setUserheight(height);
        item.setUserweight(weight);
        item.setStepsPerMile(stepsPerMile);
        item.setLevelOfActivity(level);
        super.create(item);
     
        return "1";
        }else{
        return "0";
        
        }
    }
    
    
}
