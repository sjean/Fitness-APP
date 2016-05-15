/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f"),
    @NamedQuery(name = "Food.findByFoodid", query = "SELECT f FROM Food f WHERE f.foodid = :foodid"),
    @NamedQuery(name = "Food.findByFoodname", query = "SELECT f FROM Food f WHERE f.foodname = :foodname"),
    @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category"),
    @NamedQuery(name = "Food.findByFoodfat", query = "SELECT f FROM Food f WHERE f.foodfat = :foodfat"),
    @NamedQuery(name = "Food.findByFoodserving", query = "SELECT f FROM Food f WHERE f.foodserving = :foodserving"),
    @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category"),
    @NamedQuery(name = "Food.findByFoodcol", query = "SELECT f FROM Food f WHERE f.foodcol = :foodcol")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foodid")
    private Integer foodid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "foodname")
    private String foodname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "foodfat")
    private String foodfat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "foodserving")
    private String foodserving;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "foodcol")
    private String foodcol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodid")
    private Collection<FoodConsumed> foodConsumedCollection;

    public Food() {
    }

    public Food(Integer foodid) {
        this.foodid = foodid;
    }

    public Food(Integer foodid, String foodname, String category, String foodfat, String foodserving, String foodcol) {
        this.foodid = foodid;
        this.foodname = foodname;
        this.category = category;
        this.foodfat = foodfat;
        this.foodserving = foodserving;
        this.foodcol = foodcol;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFoodfat() {
        return foodfat;
    }

    public void setFoodfat(String foodfat) {
        this.foodfat = foodfat;
    }

    public String getFoodserving() {
        return foodserving;
    }

    public void setFoodserving(String foodserving) {
        this.foodserving = foodserving;
    }

    public String getFoodcol() {
        return foodcol;
    }

    public void setFoodcol(String foodcol) {
        this.foodcol = foodcol;
    }

    @XmlTransient
    public Collection<FoodConsumed> getFoodConsumedCollection() {
        return foodConsumedCollection;
    }

    public void setFoodConsumedCollection(Collection<FoodConsumed> foodConsumedCollection) {
        this.foodConsumedCollection = foodConsumedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodid != null ? foodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodid == null && other.foodid != null) || (this.foodid != null && !this.foodid.equals(other.foodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.health.Food[ foodid=" + foodid + " ]";
    }
    
}
