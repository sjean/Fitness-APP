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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "food_consumed")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FoodConsumed.findAll", query = "SELECT f FROM FoodConsumed f"),
    @NamedQuery(name = "FoodConsumed.findByConsumedid", query = "SELECT f FROM FoodConsumed f WHERE f.consumedid = :consumedid"),
    @NamedQuery(name = "FoodConsumed.findBycount", query = "SELECT f FROM FoodConsumed f WHERE f.count = :count"),
    @NamedQuery(name = "FoodConsumed.findByDate", query = "SELECT f FROM FoodConsumed f WHERE f.date = :date")})
public class FoodConsumed implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consumedid")
    private Integer consumedid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "count")
    private Integer count;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private User userid;
    @JoinColumn(name = "foodid", referencedColumnName = "foodid")
    @ManyToOne(optional = false)
    private Food foodid;

    public FoodConsumed() {
    }

    public FoodConsumed(Integer consumedid) {
        this.consumedid = consumedid;
    }

    public FoodConsumed(Integer consumedid, Date date) {
        this.consumedid = consumedid;
        this.date = date;
    }

    public Integer getConsumedid() {
        return consumedid;
    }

    public void setConsumedid(Integer consumedid) {
        this.consumedid = consumedid;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Food getFoodid() {
        return foodid;
    }

    public void setFoodid(Food foodid) {
        this.foodid = foodid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consumedid != null ? consumedid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodConsumed)) {
            return false;
        }
        FoodConsumed other = (FoodConsumed) object;
        if ((this.consumedid == null && other.consumedid != null) || (this.consumedid != null && !this.consumedid.equals(other.consumedid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.health.FoodConsumed[ consumedid=" + consumedid + " ]";
    }
    
}
