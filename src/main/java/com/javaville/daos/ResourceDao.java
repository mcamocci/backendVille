/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaville.daos;

import com.javaville.entities.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author meena
 */
public interface ResourceDao extends CrudRepository<Resource,Long>{
    
}
