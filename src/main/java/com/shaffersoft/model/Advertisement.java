package com.shaffersoft.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ADVERTISEMENT")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADVERTISEMENT_ID")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="text")
    private String text;

    @ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(name="NEWSPAPER_ADVERTISEMENT",
            joinColumns={@JoinColumn(name="ADVERTISEMENT_ID")},
            inverseJoinColumns={@JoinColumn(name="NEWSPAPER_ID")})
    private Set<Newspaper> newspapers = new HashSet<Newspaper>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Newspaper> getNewspapers() {
        return newspapers;
    }

    public void setNewspapers(Set<Newspaper> newspapers) {
        this.newspapers = newspapers;
    }

    @Transient
    public void addNewspaper(Newspaper newspaper){
        newspapers.add(newspaper);
        newspaper.addAdvertisement(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
