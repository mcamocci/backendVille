/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javaville.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author meena
 */
@Controller
@RequestMapping("/")
public class htmlController {
    
    
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request,Model model,HttpSession session){
        
        
        String email=null;
        String password=null;
        
        try{
            
         email=request.getParameter("email");
         password=request.getParameter("password");
        
        }catch(Exception ex){           
          System.out.println("the error is:");  
         System.out.println(ex.getMessage());        
        }
               
        if(email.length()>15){        
            model.addAttribute("username","emmanuel");
            return new ModelAndView("redirect:/home/page");              
        }else{
            model.addAttribute("error","wrong email or password");           
        }
        return new ModelAndView("login");
    }
    
    @RequestMapping("/home/page")
    public String homePage(){
        
        return "home";
    }
    
    @RequestMapping("/log/user")
    public String login(){
        
        return "login";
    }
   
    @RequestMapping("/logout")
    public void logoutUser(HttpSession session){
        session.invalidate();
    }
}
