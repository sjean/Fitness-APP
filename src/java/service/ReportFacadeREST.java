/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import com.health.Food;
import com.health.Report;
import com.health.User;
import com.health.FoodConsumed;
import java.text.DateFormat;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hp
 */
@Stateless
@Path("com.health.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "healthPU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    //******************Static Query***********************
    @GET
    @Path("findByReportid/{reportid}")
    @Produces({"application/json"})
    public List<Report> findByReportid(@PathParam("reportid") int reportid) {
        Query query =em.createNamedQuery("Report.findByReportid");
        query.setParameter("reportid",reportid);
        return query.getResultList();
    }
    
   @GET
   @Path("findByUserid/{userid}")
   @Produces({"application/json"})
   public List<Report> findByUserid(@PathParam("userid") int userid) {
       Query query =em.createNamedQuery("Report.findByUserid");
       query.setParameter("userid",userid);
       return query.getResultList();
   }
    
    @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<Report> findByUsername(@PathParam("username") String username) {
        Query query =em.createNamedQuery("Report.findByUsername");
        query.setParameter("username",username);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalorieConsumed/{calorieConsumed}")
    @Produces({"application/json"})
    public List<Report> findByCalorieConsumed(@PathParam("calorieConsumed") int calorieConsumed) {
        Query query =em.createNamedQuery("Report.findByCalorieConsumed");
        query.setParameter("calorieConsumed",calorieConsumed);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalorieBurned/{calorieBurned}")
    @Produces({"application/json"})
    public List<Report> findByCalorieBurned(@PathParam("calorieBurned") int calorieBurned) {
        Query query =em.createNamedQuery("Report.findByCalorieBurned");
        query.setParameter("calorieBurned",calorieBurned);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalsteps/{totalSteps}")
    @Produces({"application/json"})
    public List<Report> findByTotalSteps(@PathParam("totalSteps") int totalSteps) {
        Query query =em.createNamedQuery("Report.findByTotalSteps");
        query.setParameter("totalSteps",totalSteps);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalorieSetGoal/{CalorieSetGoal}")
    @Produces({"application/json"})
    public List<Report> findByCalorieSetGoal(@PathParam("calorieSetGoal") int calorieSetGoal) {
        Query query =em.createNamedQuery("Report.findByCalorieSetGoal");
        query.setParameter("calorieSetGoal",calorieSetGoal);
        return query.getResultList();
    }
    
    @GET
    @Path("findByRemaining/{remaining}")
    @Produces({"application/json"})
    public List<Report> findByRemaining(@PathParam("remaining") int remaining) {
        Query query =em.createNamedQuery("Report.findByRemaining");
        query.setParameter("remaining",remaining);
        return query.getResultList();
    }
    //*****************************Combination Static*************************
    @GET
    @Path("findByUserheightANDUserweight/{userheight}/{userweight}")
    @Produces({"application/json"})
    public List<Report> findByUserheightANDUserweight(@PathParam("userheight") String userheight,@PathParam("userweight") String userweight) {
        Query query
                = em.createNamedQuery("Report.findByUserheightANDUserweight");
        query.setParameter("userheight", userheight);
        query.setParameter("userweight", userweight);
        return query.getResultList();
    }

    //**************************************Dynamic Query******************************
    @GET
    @Path("findReportByUserage/{userage}")
    @Produces({"application/json"})
    public List<Report> findReportByUserAge(@PathParam("userAge") String userage) {
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.userid.userage = '"+ userage +"'", Report.class);
        return query.getResultList();
    }
    
    @GET
    @Path("findByRemainingANDUsername/{remaining}/{username}")
    @Produces({"application/json"})
    public List<Report> findByRemainingANDUsername(@PathParam("remaining") int remaining,@PathParam("username") String username) {
        String info = remaining + ":" + username;
        System.out.println("info:" + info);
        TypedQuery<Report> query = em.createQuery("SELECT c FROM Report c WHERE c.remaining = '" +remaining + "' and c.username = '" +username+ "'" , Report.class);
        List<Report> results = query.getResultList();
        System.out.println("size:" + results.size());
        return results;
    }
    
    
    
    
    
//*********************************Generate Report**************************
//    @GET
//    @Path("generateReport/{userid}/{date}/{totalSteps}/{clorieSetGoal}")
//    @Produces({"application/json"})
//    public Report generateReport(@PathParam("userid") int userid,@PathParam("date") String date, @PathParam("totalSteps") int totalSteps,@PathParam("clorieSetGoal") double calorieSetGoal) throws ParseException {
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
//        Date dateTime = sdf.parse(date);
////        double calorieConsumed = Double.parseDouble(calculateCaloriesFromFood(userid, date));
//        double restCalorieBurned = Double.parseDouble(calculateTotalDailyCalorieBurned(userid));
//        double stepCalorieBurned = Double.parseDouble(this.calculateCaloriesPerStepsTaken(userid))*totalSteps;
//        double totalCalorieBurned = restCalorieBurned+stepCalorieBurned;
////        double remainingCalorie = totalCalorieBurned - calorieConsumed-calorieSetGoal;
//        Report item = new Report(); 
//       
////        TypedQuery<Report> id = em.createQuery("select last_insert_id(id) from Report",Report.class);
////        Report it = id.getSingleResult();
////        int i = it.getReportid();
//    
//        
//  //      TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userid ="+userid,User.class);
//        TypedQuery<User> q = em.createQuery("SELECT f FROM User f WHERE f.userid ="+userid,User.class);
//        User r = q.getSingleResult();
//        item.setUsername(r.getUsername());
//        item.setUserid(new User(userid)); 
//        item.setCalorieBurned(totalCalorieBurned);
//        item.setCalorieConsumed(calorieConsumed);
//        item.setTotalSteps(totalSteps);
//        item.setCalorieSetGoal(calorieSetGoal);
//        item.setRemaining(remainingCalorie);
//        item.setDate(dateTime);
//        super.create(item);
//        return item;
//    }
    
    //********************************BMR**************************************************************************
    @GET
    @Path("calculateBMR/{userid}")
    @Produces({"application/json"})
    public String calculateBMR(@PathParam("userid") int userid){
        Query query = em.createNamedQuery("User.findByUserid");
        query.setParameter("userid", userid);
        DecimalFormat df = new DecimalFormat("0.000");
        User result = (User)query.getSingleResult();
            String weight = result.getUserweight();
            String height = result.getUserheight();
            String age = result.getUserage();
        if(result.getUsergender().equals("male")){
            return df.format(13.75*Integer.parseInt(weight)+5*Integer.parseInt(height)-6.76*Integer.parseInt(age)+66);
        }else{
                return df.format(9.56*Integer.parseInt(weight)+1.85*Integer.parseInt(height)-4.68*Integer.parseInt(age)+665);
        }
    }
    
    //**********************Total Daily Calorie Burned**********************************
    @GET
    @Path("calculateTotalDailyCalorieBurned/{userid}")
    @Produces({"application/json"})
    public String calculateTotalDailyCalorieBurned(@PathParam("userid") int userid){
        double BMR = Double.parseDouble(calculateBMR(userid));
        double calorieTotalDailyBurned=0;
        DecimalFormat df = new DecimalFormat("0.0000");
        Query q = em.createQuery("select u from User u where u.userid = "+userid);
        User user = (User)q.getSingleResult();
        String level = user.getLevelOfActivity();
        int i = Integer.parseInt(level);
        switch(i){
            case 1:
                calorieTotalDailyBurned = BMR*1.2;
                break;
            case 2:
                calorieTotalDailyBurned = BMR*1.375;
                break;
            case 3:
                calorieTotalDailyBurned = BMR*1.55;
                break;
            case 4:
                calorieTotalDailyBurned = BMR*1.725;
                break;
            case 5:
                calorieTotalDailyBurned = BMR*1.9;
                break;
        }
        return df.format(calorieTotalDailyBurned);
    }
    
    //******************************Calories Per Steps Taken*************************
    @GET
    @Path("calculateCaloriesPerStepsTaken/{userid}")
    @Produces({"application/json"})
    public String calculateCaloriesPerStepsTaken(@PathParam("userid") int userid){
        DecimalFormat df = new DecimalFormat("0.0000");
        Query query = em.createNamedQuery("User.findByUserid");
        query.setParameter("userid",userid);
        User user = (User)query.getSingleResult();
        double average = Integer.parseInt(user.getUserweight())*0.49*2.2046/Integer.parseInt(user.getStepsPerMile());
        return df.format(average);
    }
   
    
 //******************************Total Calories Burned and Consumed One Day*************************    
    @GET
    @Path("calculateTotalCaloriesBurnedAndConsumedOneDay/{username}/{date}")
    @Produces({"application/json"})
    public String calculateTotalCaloriesBurnedAndConsumedOneDay(@PathParam("username") String username,@PathParam("date") String date){
        DecimalFormat df = new DecimalFormat("0.0");
        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.username like"+" '"+username+"' AND r.date like "+"'%"+date+"%'",Report.class);
        System.out.println(q);
        Report r = (Report)q.getSingleResult();
        String result = df.format(r.getCalorieBurned());
        return result;
    }
//    @GET
//    @Path("calculateTotalCaloriesBurnedAndConsumedOneDay/{username}/{date}")
//    @Produces({"application/json"})
//    public String calculateTotalCaloriesBurnedAndConsumedOneDay(@PathParam("username") String username,@PathParam("date") String date){
//        DecimalFormat df = new DecimalFormat("0.0");
//        TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.username like"+" '"+username+"' AND r.date like "+"'%"+date+"%'",Report.class);
//        System.out.println(q);
//        Report r = (Report)q.getSingleResult();
//        String result = "{totalCalorieBurned :"+df.format(r.getCalorieBurned())+"} {calorieConsume:"+df.format(r.getCalorieConsumed())+"}}";
//        return result;
//    }
    
 //******************************Total Calories Burned and Consumed One Period*************************     
   @GET
   @Path("calculateCalorieBurnedAndConsumeOnPeriod/{userid}/{begindate}/{enddate}")
   @Produces({"application/json"})
   public String calculateCalorieBurnedAndConsumeOnPeriod(@PathParam("userid") int userid,@PathParam("begindate") String begindate,@PathParam("enddate") String enddate) throws ParseException{
       DecimalFormat df = new DecimalFormat("0.0000");
       TypedQuery<Report> q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid ="+userid+" AND r.date between '"+begindate+"' AND '"+enddate+"'",Report.class);
       List<Report> l =q.getResultList();
       double totalCalorieBurned= 0;
       double totalCalorieConsumed = 0;
       for(Report r : l){
           totalCalorieBurned+=r.getCalorieBurned();
           totalCalorieConsumed+=r.getCalorieConsumed();
       }
       String result = "{TotalCalorieBurned :"+df.format(totalCalorieBurned)+"} {TotalCalorieConsumed:"+df.format(totalCalorieConsumed)+"}";
       return result;
   }



 

    
   
   
//******************************Calories From Total Food**********************************
     @GET
    @Path("calculateCaloriesFromTotalFood/{username}/{date}")
    @Produces({"application/json"})
    public String calculateCaloriesFromFood(@PathParam("username") String username,@PathParam("date") String date){
        DecimalFormat df = new DecimalFormat("0.0");
        TypedQuery<FoodConsumed> q = em.createQuery("SELECT c FROM FoodConsumed c WHERE c.userid.username = '"+username+"' AND "+" c.date like "+"'%"+date+"%'",FoodConsumed.class);
        double totalCalories = 0;
        List <FoodConsumed> result = q.getResultList();
        for(FoodConsumed r :result){
            totalCalories+=Double.parseDouble(r.getFoodid().getFoodcol());
        }
        return df.format(totalCalories);
    } 
   
    public User getUserByUserName(String userName){
        Query query =em.createNamedQuery("User.findByUsername");
        query.setParameter("username",userName);
        User u = (User)query.getResultList().get(0);
        return u;
    }
    
                                                        
    @GET
    @Path("AddReport/{username}/{calorieSetGoal}/{date}")
    @Produces({"application/json"})
    public String AddReport(@PathParam("username") String username,
            @PathParam("calorieSetGoal") double calorieSetGoal, 
            @PathParam("date") String date){
        try{
        User user = getUserByUserName(username);
        Report item = new Report();
        item.setUserid(user);
        item.setUsername(username);
        item.setCalorieSetGoal(calorieSetGoal);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = format.parse(date);
            item.setDate(parse);
        } catch (ParseException ex) {
            Logger.getLogger(ReportFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.create(item);
        return "1";}catch(Exception e){
          return "0";
        }
    }

    
    
    @GET
    @Path("UpdateCalorie/{username}/{calorieNewGoal}/{date}")
    @Produces({"application/json"})
    public String UpdateCalorie(@PathParam("username") String username,@PathParam("calorieNewGoal") double calorieNewGoal,
                                @PathParam("date") String date){
        
        try{
            
            TypedQuery<Report> query = em.createQuery(   "UPDATE Report r SET r.calorieSetGoal = '" + calorieNewGoal + "'"
                    + " WHERE r.username = '" + username + "' and r.date = '" + date + "'", Report.class);

            query.executeUpdate();
//            Query query = em.createNamedQuery("Report.findByUsername");
//            query.setParameter("username", username);
//            Report item = (Report)query.getResultList().get(0);
//            item.setCalorieSetGoal(calorieNewGoal);
       
            return "1";}catch(Exception e){
                           return "0";
        }
    }
    @GET
    @Path("UpdateSteps/{username}/{totalSteps}/{date}")
    @Produces({"application/json"})
    public String UpdateSteps(@PathParam("username") String username,@PathParam("totalSteps") double totalSteps,
                                @PathParam("date") String date){
        
        try{
            TypedQuery<Report> query = em.createQuery(   "UPDATE Report r SET r.totalSteps = '" + totalSteps + "'"
                    + " WHERE r.username = '" + username + "' and r.date = '" + date + "'", Report.class);
            query.executeUpdate();
            return "1";}catch(Exception e){
                           return "0";
        }
    }
    
    
    
    @GET
    @Path("findByDateANDUsername/{date}/{username}")
    @Produces({"application/json"})
    public List<Report> findByDateANDUsername(@PathParam("date") String date,@PathParam("username") String username) {
     
        TypedQuery<Report> query = em.createQuery("SELECT c FROM Report c WHERE c.date LIKE " +" '%"+date+"%'  and c.username = '" +username+ "'" , Report.class);
        List<Report> results = query.getResultList();
        return results;
    }
}   

