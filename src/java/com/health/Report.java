/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByReportid", query = "SELECT r FROM Report r WHERE r.reportid = :reportid"),
    @NamedQuery(name = "Report.findByUserheightANDUserweight", query = "SELECT r FROM Report r where r.userid.userheight = :userheight AND r.userid.userweight = :userweight "),
    @NamedQuery(name = "Report.findByUsername", query = "SELECT r FROM Report r WHERE r.username = :username"),
    @NamedQuery(name = "Report.findByCalorieConsumed", query = "SELECT r FROM Report r WHERE r.calorieConsumed = :calorieConsumed"),
    @NamedQuery(name = "Report.findByCalorieBurned", query = "SELECT r FROM Report r WHERE r.calorieBurned = :calorieBurned"),
    @NamedQuery(name = "Report.findByTotalSteps", query = "SELECT r FROM Report r WHERE r.totalSteps = :totalSteps"),
    @NamedQuery(name = "Report.findByCalorieSetGoal", query = "SELECT r FROM Report r WHERE r.calorieSetGoal = :calorieSetGoal"),
    @NamedQuery(name = "Report.findByRemaining", query = "SELECT r FROM Report r WHERE r.remaining = :remaining"),
    @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reportid")
    private Integer reportid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calorie_consumed")
    private Double calorieConsumed;
    @Column(name = "calorie_burned")
    private Double calorieBurned;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_steps")
    private double totalSteps;
    @Column(name = "calorie_set_goal")
    private Double calorieSetGoal;
    @Column(name = "remaining")
    private Double remaining;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private User userid;

    public Report() {
    }

    public Report(Integer reportid) {
        this.reportid = reportid;
    }

    public Report(Integer reportid, String username, double totalSteps) {
        this.reportid = reportid;
        this.username = username;
        this.totalSteps = totalSteps;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getCalorieConsumed() {
        return calorieConsumed;
    }

    public void setCalorieConsumed(Double calorieConsumed) {
        this.calorieConsumed = calorieConsumed;
    }

    public Double getCalorieBurned() {
        return calorieBurned;
    }

    public void setCalorieBurned(Double calorieBurned) {
        this.calorieBurned = calorieBurned;
    }

    public double getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(double totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Double getCalorieSetGoal() {
        return calorieSetGoal;
    }

    public void setCalorieSetGoal(Double calorieSetGoal) {
        this.calorieSetGoal = calorieSetGoal;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportid != null ? reportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportid == null && other.reportid != null) || (this.reportid != null && !this.reportid.equals(other.reportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.health.Report[ reportid=" + reportid + " ]";
    }

    public void setCalorieConsumed(String calorieConsumed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
