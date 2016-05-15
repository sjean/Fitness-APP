/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.health.Food;
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
 *
 * @author hp
 */
@Stateless
@Path("com.health.food")
public class FoodFacadeREST extends AbstractFacade<Food> {

    @PersistenceContext(unitName = "healthPU")
    private EntityManager em;

    public FoodFacadeREST() {
        super(Food.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Food entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Food entity) {
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
    public Food find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByFoodid/{foodid}")
    @Produces({"application/json"})
    public List<Food> findByFoodid(@PathParam("foodid") Integer foodid) {
        Query query =em.createNamedQuery("Food.findByFoodid");
        query.setParameter("foodid",foodid);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFoodname/{foodname}")
    @Produces({"application/json"})
    public List<Food> findByFoodname(@PathParam("foodname") String foodname) {
        TypedQuery<Food> q = em.createQuery("SELECT f FROM Food f WHERE f.foodname like "+"'%"+foodname+"%'",Food.class);
        return q.getResultList();
    }
    
    @GET
    @Path("findByFoodfat/{foodfat}")
    @Produces({"application/json"})
    public List<Food> findByFoodfat(@PathParam("foodfat") String foodfat){
        Query query = em.createNamedQuery("Food.findByFoodfat");
        query.setParameter("foodfat", foodfat);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFoodserving/{foodserving}")
    @Produces({"application/json"})
    public List<Food> findByFoodserving(@PathParam("foodserving") String foodserving){
        Query query = em.createNamedQuery("Food.findByFoodserving");
        query.setParameter("foodserving", foodserving);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFoodcol/{foodcol}")
    @Produces({"application/json"})
    public List<Food> findByFoodcol(@PathParam("foodcol") String foodcol){
        Query query = em.createNamedQuery("Food.findByFoodcol");
        query.setParameter("foodcol", foodcol);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCategory/{category}")
    @Produces({"application/json"})
    public List<Food> findByCategory(@PathParam("category") String category){
        Query query = em.createNamedQuery("Food.findByCategory");
        query.setParameter("category", category);
        return query.getResultList();
    }
    
  
    
}
