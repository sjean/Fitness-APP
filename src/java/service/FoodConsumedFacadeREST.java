/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.health.Food;
import com.health.FoodConsumed;
import com.health.User;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
@Path("com.health.foodconsumed")
public class FoodConsumedFacadeREST extends AbstractFacade<FoodConsumed> {

    @PersistenceContext(unitName = "healthPU")
    private EntityManager em;

    public FoodConsumedFacadeREST() {
        super(FoodConsumed.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(FoodConsumed entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, FoodConsumed entity) {
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
    public FoodConsumed find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<FoodConsumed> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<FoodConsumed> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByConsumedid/{consumedid}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByConsumedid(@PathParam("consumedid") int consumedid) {
        Query query = em.createNamedQuery("FoodConsumed.findByConsumedid");
        query.setParameter("consumedid", consumedid);
        return query.getResultList();
    }

    @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByUsername(@PathParam("username") String username) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.userid.username like " + "'%" + username + "%'", FoodConsumed.class);
        return q.getResultList();
    }

    @GET
    @Path("findByFoodname/{foodname}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByFoodname(@PathParam("foodname") String foodname) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT f FROM FoodConsumed f WHERE f.foodid.foodname like " + "'%" + foodname + "%'", FoodConsumed.class);
        return q.getResultList();
    }

    @GET
    @Path("findByDate/{date}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByDate(@PathParam("date") String date) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.date like " + "'%" + date + "%'", FoodConsumed.class);
        return q.getResultList();
    }
    
    @GET
    @Path("findByCount/{count}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByCount(@PathParam("count") String count) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.count =" + "'" + count+ "'", FoodConsumed.class);
        return q.getResultList();
    }
    @GET
    @Path("findByFoodID/{foodid}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByFoodID(@PathParam("foodid") int foodid) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.foodid.foodid =" + "'" + foodid+ "'", FoodConsumed.class);
        return q.getResultList();
    }

    
    //**************************************Dynamic Query**********************************************************************
    @GET
    @Path("findByConsumedidANDDate/{consumedid}/{date}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByConsumedidANDDate(@PathParam("consumedid") int consumedid, @PathParam("date") String date) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.consumedid =  '" + consumedid + "'  AND c.date like " + "'%" + date + "%'", FoodConsumed.class);
        return q.getResultList();
    }
    
    @GET
    @Path("findByUserageANDDate/{userage}/{date}")
    @Produces({"application/json"})
    public List<FoodConsumed> findByUserageANDDate(@PathParam("userage") int userage, @PathParam("date") String date) {
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.userid.userage = '" + userage + "' AND c.date like " + "'%" + date + "%'", FoodConsumed.class);
        return q.getResultList();
    }
    
 //**************************************Adding a Food********************************************************************** 
    @GET
    @Path("addItem/{userid}/{foodid}/{date}")
    @Produces({"application/json"})
    public List<FoodConsumed> addItem(@PathParam("userid") int userid,@PathParam("foodid") int foodid,@PathParam("date") String date) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
        Date datetime = sdf.parse(date);
        FoodConsumed item = new FoodConsumed();
        item.setFoodid(new Food(foodid));
        item.setUserid(new User(userid));
        item.setDate(datetime);
        super.create(item);
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.userid.userid = "+userid+" AND c.foodid.foodid = "+foodid+" AND c.date like "+"'%"+date+"%'",FoodConsumed.class);
        return q.getResultList();
    }
    
    
      //******************************Calories From Total Food*************************
    
     @GET
    @Path("calculateCaloriesFromTotalFood/{userid}/{date}")
    @Produces({"application/json"})
    public String calculateCaloriesFromFood(@PathParam("userid") int userid,@PathParam("date") String date){
        DecimalFormat df = new DecimalFormat("0.0000");
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.userid.userid = '"+userid+"' AND "+" c.date like "+"'%"+date+"%'",FoodConsumed.class);
        double totalCalories = 0;
        List <FoodConsumed> result = q.getResultList();
        for(FoodConsumed r :result){
            totalCalories+=Double.parseDouble(r.getFoodid().getFoodcol());
        }
        return df.format(totalCalories);
    } 
    @GET
    @Path("AddFoodCount/{username}/{foodname}/{date}/{count}")
    @Produces({"application/json"})
    public String AddFoodCount(@PathParam("count") int count,@PathParam("count") String username,@PathParam("count") String foodname,
                            @PathParam("date") String date){
        
//       try{ 
        TypedQuery<FoodConsumed> q = em.createQuery("UPDATE FoodConsumed c SET c.count = "+count
                  +"  WHERE c.userid.username= '"+username+"' AND c.foodid.foodname= '"+foodname+"' "
                  + "AND c.date LIKE "+"'%"+date+"%'",FoodConsumed.class);
        q.executeUpdate();
        return "1";
       }
//       catch(Exception e){
//        return "0";}
//    } 
    
}
