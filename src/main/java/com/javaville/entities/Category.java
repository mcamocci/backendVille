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
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author meena
 */
@Entity
public class Category implements Serializable,Comparable{
    
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
   
    private String description;
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
    
    @JsonIgnore
    @OneToMany(mappedBy="category")
    private List<CategoryItem> categoryItems=new ArrayList<>();
    
   
    
    public void setId(Long id){
        this.id=id;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public Long getId(){
        return this.id;
    }
    
   
    
    public String getName(){
        return this.name;
    }
    
   

    @Override
    public int compareTo(Object o) {
        //To change body of generated methods, choose Tools | Templates.
        Category cat=(Category)o;
        if(cat.getName().equalsIgnoreCase(this.getName())){
             return 0;
        }
        return 1;
        
    }
    
    public void addCategoryItem(CategoryItem item){
        this.categoryItems.add(item);
    }
    
    public List<CategoryItem> getCategoryItems(){
        return this.categoryItems;
    }
}
