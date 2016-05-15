package com.example.hp.health.Db;

/**
 * Created by hp on 23/04/2016.
 */
public class ServerUser {
    private String username;
    private String password;
    private String gender;
    private String age;
    private String height;
    private String weight;
    private String stepsPerMile;
    private String level;

    public ServerUser(String username,String password,String gender,String age,String height, String weight, String  stepsPerMile, String level){
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.stepsPerMile = stepsPerMile;
        this.level = level;
    }

    public ServerUser() {

    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getGender(){
        return gender;
    }
    public String getAge(){
        return age;
    }
    public String getHeight(){
        return height;
    }
    public String getWeight(){
        return  weight;
    }
    public String getStepsPerMile(){
        return stepsPerMile;
    }
    public String getLevel(){
        return level;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void  setGender(String gender){
        this.gender = gender;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setHeight(String height){
        this.height = height;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public void setStepsPerMile(String stepsPerMile){
        this.stepsPerMile = stepsPerMile;
    }
    public void setLevel(String level){
        this.level = level;
    }
}
