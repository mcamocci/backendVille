/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaville.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author meena
 */
@Entity
public class Post implements Serializable {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Uploader getPoster() {
        return uploader;
    }

    public void setPoster(Uploader poster) {
        this.uploader= poster;
    }
    
    @Id  
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String content;
    
    @Column(nullable=false,unique=false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @ManyToOne
    @JoinColumn(name="poster_id")
    private Uploader uploader;
    
    @OneToMany(mappedBy="post")
    private Collection<Comment> comments=new ArrayList();
    
    @JsonManagedReference
    @ManyToOne 
    private CategoryItem categoryItem;
    
    @OneToMany(mappedBy="post")
    Collection<Resource> resources=new ArrayList();
    
    public void addResource(Resource resource){
        this.resources.add(resource);
    }
    
    public void setCategory(CategoryItem categoryItem){
        this.categoryItem=categoryItem;
    }
    
}
