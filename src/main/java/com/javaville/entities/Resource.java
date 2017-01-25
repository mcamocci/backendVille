/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaville.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author meena
 */
@Entity
public class Resource implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String url;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Date date;
    private String type;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="post_id")
    private Post post;
    
    public Post getPost(){
        return this.post;
    }
    
    public void setPost(Post post){
        this.post=post;
     }
    
    public void setUrl(String url){
        this.url=url;
    }
    
    public String getUrl(){
        return this.url;
    }
    
    public void setDate(Date date){
        this.date=date;
    }
    
    public void setType(String type){
        this.type=type;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public String getType(){
        return this.type;
    }
    
}
