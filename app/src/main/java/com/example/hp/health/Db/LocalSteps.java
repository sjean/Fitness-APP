package com.example.hp.health.Db;

/**
 * Created by hp on 25/04/2016.
 */
public class LocalSteps {
    public static final String TABLE_NAME = "Localsteps";
    public static final String RECORD_ID = "id";
    public static final String TIME = "time";
    public static final String STEPS = "steps";
    public static final String USER_ID = "user_id";

    public static final String CREATE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    TIME + " TEXT NOT NULL, " +
                    STEPS + " INTEGER NOT NULL, " +
                    USER_ID+" INTEGER NOT NULL, "+
                    "FOREIGN KEY("+USER_ID+") REFERENCES "+ LocalUser.TABLE_NAME+"("+ LocalUser.USER_ID+
                    ")"
                    +")";
    private int stepId;
    private String time;
    private long step;
    private int userId;

    public LocalSteps(String time,long step, int userId){
        this.userId=userId;
        this.step=step;
        this.time=time;
    }
    public LocalSteps(int stepId,String time,long step, int userId){
        this.stepId=stepId;
        this.userId=userId;
        this.step=step;
        this.time=time;
    }

    public long getStep(){
        return step;
    }

    public int getStepId(){
        return stepId;
    }
    public int getUserId(){
        return userId;
    }
    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time=time;
    }
    public void setUserId(int userId){
       this.userId=userId;
    }
    public void setStep(long step){
        this.step=step;
    }
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }


}
