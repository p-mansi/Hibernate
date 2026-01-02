package com.example.hibernate_example.Model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// import jakarta.persistence.Table;

@Entity
// @Entity(name="Alien_Table")
// @Table(name = "Alien_Table")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Alien {

    @Id
    private int aid;
    // @Transient // used for not adding this column to the database
    private AlienName aname;

    // @Column(name = "alien_color") // for changing name of column
    private String color;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public AlienName getAname() {
        return aname;
    }

    public void setAname(AlienName aname) {
        this.aname = aname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Alien [aid= " + aid + ", aname= " + aname + ", color= " + color + "]";
    }

}
