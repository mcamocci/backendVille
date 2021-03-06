/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaville.daos;

import com.javaville.entities.Category;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author meena
 */
@Repository
public interface CategoryDao  extends CrudRepository<Category,Long> {
    Category findByName(String string);
    List<Category> findAllByName(String string);
}
