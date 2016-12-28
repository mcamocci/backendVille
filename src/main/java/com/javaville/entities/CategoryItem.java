/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaville.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author meena
 */
@Entity
public class CategoryItem implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String description;
    private String name;
    
    @ManyToOne
    private Category category;
    
    @JsonBackReference
    @OneToMany(mappedBy="categoryItem")
    private List<Post> post=new ArrayList<>();
    
    
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    
    public void setId(Long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCategory(Category category){
        this.category=category;
    }
    
     public void setPost(Post post){
        this.post.add(post);
    }
    
    @JsonIgnore
    public List<Post> getPost(){
        return this.post;
    }
}
