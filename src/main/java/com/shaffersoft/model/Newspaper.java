package com.shaffersoft.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="NEWSPAPER")
public class Newspaper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="state")
    private String state;

    @ManyToMany(fetch=FetchType.EAGER, mappedBy="newspapers")
    private Set<Advertisement> advertisements = new HashSet<Advertisement>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    @Override
    public String toString(){
        return name;
    }

    @Transient
    public void addAdvertisement(Advertisement advertisement){
        advertisements.add(advertisement);
    }
}
