package com.example.hp.health.Db;

/**
 * Created by hp on 21/04/2016.
 */
public class URL {
    public final static String URL_retriveFromDB ="http://192.168.56.1:8080/health/webresources/com.health.user/findByUsername/";

    public final static String URL_Signup = "http://192.168.56.1:8080/health/webresources/com.health.user/SignUp/";

    public final static String URL_GetGoal = "http://192.168.56.1:8080/health/webresources/com.health.report/findByDateANDUsername/";

    public final static String URL_GetTotalSteps = "http://192.168.56.1:8080/health/webresources/com.health.report/findByDateANDUsername/";

    public final static String URL_AddReport = "http://192.168.56.1:8080/health/webresources/com.health.report/AddReport/";

    public final static String URL_UpdateCalorie = "http://192.168.56.1:8080/health/webresources/com.health.report/UpdateCalorie/";

    public final static String URL_TotalSteps = "http://192.168.56.1:8080/health/webresources/com.health.report/UpdateSteps/";

    public final static String URL_Category = "http://192.168.56.1:8080/health/webresources/com.health.food/findByCategory/";

    public final static String URL_Consumed = "http://192.168.56.1:8080/health/webresources/com.health.report/calculateCaloriesFromTotalFood/";

    public final static String URL_StepsBurned= "http://192.168.56.1:8080/health/webresources/com.health.report/calculateCaloriesTotalStepsTaken/";

    public final static String URL_Burned = "http://192.168.56.1:8080/health/webresources/com.health.report/calculateTotalCaloriesBurnedAndConsumedOneDay/";

    public final static String URL_AddFoodCount = "http://192.168.56.1:8080/health/webresources/com.health.foodconsumed/AddFoodCount/";



//    public static String URL_findByUsername = ;
//    public static String URL_findByUsernameAndPassword;
}
