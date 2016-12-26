package com.javaville.controllers;
import com.javaville.daos.CategoryDao;
import com.javaville.daos.PostsDao;
import com.javaville.daos.UploaderDao;
import com.javaville.daos.VisiterDao;
import com.javaville.entities.Category;
import com.javaville.entities.Comment;
import com.javaville.entities.Post;
import com.javaville.entities.Resource;
import com.javaville.entities.Uploader;
import com.javaville.entities.Visiter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resources;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author meena
 */

@RestController
@RequestMapping("/app")
public class ControllerRest {
   
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    PostsDao postDao;
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    VisiterDao visiterDao;
    
    @Autowired
    UploaderDao uploaderDao;
    
    @RequestMapping("/demo/ps")
    public String demo(){
    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
         return encoder.encode("something encoded");
    }
    @RequestMapping("/categories")
    public Collection<Category> getAllCategories(){
    
        Collection<Category> list=(ArrayList<Category>) categoryDao.findAll();        
        return list;
    
    }
    
    @RequestMapping("/category")
    public Category getAllCategory(){
    
        Category cat=(Category) categoryDao.findByName("java");        
        return cat;
    
    }
    
    @RequestMapping("/distinct/cat")
    public Set<Category>  getAllDistCat(){
        
         Query querry=entityManager.createQuery("from Category");  
         List<Category> categories=(List<Category>)querry.getResultList();
         
         Set<Category> categories0=new TreeSet<>();
         for(Category cat:categories){
             categories0.add(cat);
         }
         
        return categories0;
    }
    
    @RequestMapping("/sub/cat/{name}")
    public List<Category>  getSubCat(@PathVariable("name") String name){
        
         TypedQuery<Category> querry=entityManager.createQuery("from Category where name=:name",Category.class);  
         querry.setParameter("name",name);
         List<Category> categories=(List<Category>)querry.getResultList();
         
         
        return categories;
    }
    
    @RequestMapping(value="/under/item/{name}/{type}",method=RequestMethod.GET)
    public List<Category> getSubCatItem(@PathVariable("name") String name,@PathVariable("type") String type){
        
         TypedQuery<Category> querry=entityManager.createQuery("from Category where name=:name and type=:type",Category.class);
         querry.setParameter("name",name);
         querry.setParameter("type",type);
         List<Category> categories=(List<Category>)querry.getResultList();
         
         System.out.println(categories.get(0).getPost().get(0).getPoster().getEmail());
         
         return categories;
    
    }
    
    @RequestMapping("/lesson/{name}/{topic}")
    public List<Post> getAllRelatedPost(@PathVariable("name") String name,@PathVariable("topic") String topic){

        TypedQuery<Category> querry=entityManager.createQuery("from Category where name=:name and type=:type",Category.class);
        querry.setParameter("name",name);
        querry.setParameter("type",topic);
        
        
        List<Category> categories=(List<Category>)querry.getResultList();   
        System.out.println(categories.get(0).getId());
        
        TypedQuery<Post> post=entityManager.createQuery("from Post where category_id=:id",Post.class);
        post.setParameter("id",categories.get(0).getId());
        
        List<Post> list=(List<Post>)post.getResultList();
        
        return list;
    }
    
    
    @RequestMapping("/getPost/{id}")
    public Post getPost(@PathVariable("id") Long id){        
        return postDao.findOne(id);
    }
    
    
    @RequestMapping("/funcode")
    @Transactional
    public String funcode(
            /*@RequestParam(value="files[]") String[] files,*/
            @RequestParam(value="resource[]") String[] resources,
            @RequestParam(value="content") String content){
        
            Post post=new Post();
            post.setContent(content);
            post.setDate(new Date());
           
            String res_contents="";
            for(String res:resources){
                
                res_contents+=res;
                Resource resource=new Resource();
                resource.setUrl(res);
                resource.setPost(post);
                entityManager.persist(resource);                
                post.addResource(resource);
                
              }
                        
            entityManager.persist(post);
            

        return  res_contents;
    }
   
    @RequestMapping("/test")
    public String first(){
        return "hello am now working and the intellij is useless";
    }
    
    
    @RequestMapping("/home")
    public String homepage(){    
        return "home";
    }
    
    /*  
    @RequestMapping("/all")
    @Transactional
   public List<Employee> getAllEmployee(){
    
    @SuppressWarnings("JPQLValidation")
    Query querry=entityManager.createQuery("from Employee");
    querry.setFirstResult(1);
    querry.setMaxResults(5);
    List<Employee> employees=(List<Employee>)querry.getResultList();
    
    return employees;
    
    }*/
   
    /*
    @RequestMapping("/save")
    @Transactional
    public Object saveObject(HttpServletRequest request){
          
        @SuppressWarnings("UnusedAssignment")
         //  EntityTransaction userTransaction=null;
            /* userTransaction=entityManager.getTransaction();
            userTransaction.begin();
            
            String name=request.getParameter("name");
            String age=request.getParameter("age");
            String salaryValue=request.getParameter("salary");
     
            Employee employee=new Employee();
            employee.setAge(Integer.parseInt(age));
            employee.setName(name); 
            
            
            Salary salary=new Salary();
            salary.setAmount(new Long(Integer.parseInt(salaryValue)));
            salary.setEmployee(employee);
            
            entityManager.persist(salary);            
            employee.setSalaries(salary);
            
            
            entityManager.persist(employee);
                 
            return "done saving info for "+name;
    } */
    
    /* @RequestMapping("/employee/{id}")
    public Employee getSalary(@PathVariable("id") Long id){
    
    
    Session session=null;
    Employee employee=null;
    
    try{
    
    session.beginTransaction();
    employee=(Employee)session.get(Employee.class, id);
    
    }catch(Exception ex){
    
    }finally{
    
    if(session!=null){
    session.close();
    }
    
    }
    return employee;
    }*/
    
    /*  @RequestMapping("/salary")
    public Object getSalarys(){
    
    
    Session session=null;
    Salary salary=null;
    
    try{
    
    session.beginTransaction();
    salary=(Salary)session.get(Salary.class,new Long(1));
    
    }catch(Exception ex){
    
    
    }finally{
    
    if(session!=null){
    session.close();
    }
    }
    return salary;
    }*/
    
    
    /*
    @RequestMapping("/amazing")
    @SuppressWarnings("null")
    public Salary trialFunc(){
    
        Session session=null;
	Salary salary=null;
        
        try{        	
            session.beginTransaction();       
            
            salary=(Salary)session.get(Salary.class,new Long(1));
        
            System.out.println(salary.getEmployee().getName());
            session.getTransaction().commit();
            
        }catch(Exception ex){       
            
            System.out.println("some problems occured");
            System.out.println(ex.toString());
            session.getTransaction().rollback();
            
        }finally{
        
            if(session!=null){
                session.close();
            }
        }        
            return salary;
    
    }

*/

    @RequestMapping("/posts")
    public Post getAllPosts(){  
        
    return new Post();
    
    }
    
    @RequestMapping("/comments")
    public Comment getAllComments(){
    
    return new Comment();
    
    }
    
    
    @RequestMapping("/new/post")
    public String createPost(HttpServletRequest request){
        
    String content=request.getParameter("content");
    String date=new Date().toString();
    
    Uploader uploader;
    Resources resources;
        
         return null;
    }
        
   @RequestMapping("/do")
    public String doTheTask(HttpSession session){
       session.setAttribute("something","am the session");
       return "something is done emmanuel";
    }
 
    @RequestMapping("/now")
    public String theSaved(HttpSession session){
        return (String)session.getAttribute("something");
    }        
    
    @RequestMapping("/clear")
    public String clearTheSession(HttpSession session){
        session.invalidate();
        return "done";
    }
    
    @RequestMapping("/loginTest")
    public ModelAndView loginTest(HttpSession session){
        
        String val=(String) session.getAttribute("something");
        
            if(val!=null){
                
             if(val.length()>4){
                    return new ModelAndView("redirect:/app/now");
                }
             
            }
        return new ModelAndView("error");
                
    }
    
    @ModelAttribute("category_name")
    public List<String> getCatNames(){
        List<Category> categories=(List<Category>)categoryDao.findAll();
        List<String> items=new ArrayList<>();
        categories.stream().forEach((cat) -> {
            items.add(cat.getName());
        });
        return items;
    }
    
    @ModelAttribute("category_type")
    public List<String> getCatType(){
        List<Category> categories=(List<Category>)categoryDao.findAll();
        List<String> items=new ArrayList<>();
        categories.stream().forEach((cat) -> {
            items.add(cat.getType());
        });
        return items;
    }
    
    
    @RequestMapping("/insert/category")
    public void insertIntoCategory(HttpServletRequest request){
        String name=request.getParameter("name");
        String cat_description=request.getParameter("cat_description");
        String sub_cat_description=request.getParameter("sub_cat_description");
        String type=request.getParameter("type");
        
        Category cat=new Category();
        cat.setName(name);
        cat.setType(type);
        cat.setSub_cat_descr(sub_cat_description);
        cat.setCat_descr(cat_description);
        categoryDao.save(cat);
    }
    
    @RequestMapping("/insert/visiter")
    public void insertVisiter(HttpServletRequest request){
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String passOne=request.getParameter("passOne");
        String passTwo=request.getParameter("passTwo");
        
        if(passOne.equals(passTwo)){
            Visiter visiter=new Visiter();
            visiter.setUsername(name);
            visiter.setEmail(email);
            visiter.setPassword(new BCryptPasswordEncoder().encode(passTwo));            
            visiterDao.save(visiter);
            
        }
        
    }
    
    @RequestMapping("/insert/admin")
    public void insertAdmin(HttpServletRequest request){
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String passOne=request.getParameter("passOne");
        String passTwo=request.getParameter("passTwo");
        
        if(passOne.equals(passTwo)){
            Uploader uploader=new Uploader();
            uploader.setName(name);
            uploader.setEmail(email);
            uploader.setPassword(new BCryptPasswordEncoder().encode(passTwo));            
            uploaderDao.save(uploader);
            
        }
        
    }
    
}