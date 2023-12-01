package edu.whu.controller;

import edu.whu.entity.Blog;
import edu.whu.entity.User;
import edu.whu.service.MySQL.MySqlHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @GetMapping("/showBlog")
    public ResponseEntity<Map<String , String>> showBlog(Integer blogId) {
        Map<String , String> result = new HashMap<>();
        //MessageController.hr();
        try {
            MySqlHelper instance = MySqlHelper.getInstance();
            Blog newBlog = instance.getInstance(Blog.class, "SELECT * FROM blogs WHERE blogId = ?", blogId);
            if (newBlog != null) {//存在该博客
                result.put("content",newBlog.getContent());
                return ResponseEntity.ok(result);
            }
            else{
                result.put("error","查找不到该博客");
                return ResponseEntity.badRequest().body(result);
            }
        }catch (Exception ex){
            result.put("error","数据库错误");
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 展示该用户所有博客
     * @param userid
     */
    @GetMapping("/getByUser/{userid}")
    public ResponseEntity<List<Blog>> showBlogs(@PathVariable int userid){
        // MessageController.message("用户 ".concat(user.getUserName()).concat(" 你好！以下是你所有发布的博客："));
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            ResultSet rs = instance.getResultSet("SELECT blogId FROM blogs WHERE userId = ?",userid);
            Vector<Integer> blogIds = new Vector<Integer>();
            while (rs.next()){
                Integer id = rs.getInt("blogId");
                blogIds.add(id);
            }
            List<Blog> result = new ArrayList<>();
            for (Integer blogId:
                 blogIds) {
                Blog blog = instance.getInstance(Blog.class,"SELECT * FROM blogs WHERE blogId = ?",blogId);
                result.add(blog);
                //BlogController.showBlog(blog);
            }
            return ResponseEntity.ok(result);
        }catch (SQLException ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){

        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Blog>> showAllBlogs(){
        // MessageController.message("用户 ".concat(user.getUserName()).concat(" 你好！以下是你所有发布的博客："));
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            ResultSet rs = instance.getResultSet("SELECT blogId FROM blogs ");
            Vector<Integer> blogIds = new Vector<Integer>();
            while (rs.next()){
                Integer id = rs.getInt("blogId");
                blogIds.add(id);
            }
            List<Blog> result = new ArrayList<>();
            for (Integer blogId:
                    blogIds) {
                Blog blog = instance.getInstance(Blog.class,"SELECT * FROM blogs WHERE blogId = ?",blogId);
                result.add(blog);
                //BlogController.showBlog(blog);
            }
            return ResponseEntity.ok(result);
        }catch (SQLException ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){

        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBlog(int userid, String userType, String content) {
        try {
            MySqlHelper instance = MySqlHelper.getInstance();
            instance.insertAndId("INSERT INTO blogs (userId, content, fromWho, time) VALUES (?, ?, ?, ?)", userid, content, userType, LocalDateTime.now());
            return ResponseEntity.ok().build();
        }catch (SQLException e){

        }catch (Exception e){

        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * 从blog表中选取10条博客
     */
//    public static void showBlogs(){
//        MessageController.message("正在刷取博客：");
//        try{
//            MySqlHelper instance = MySqlHelper.getInstance();
//            ResultSet rs = instance.getResultSet("SELECT blogId FROM blogs");
//            Vector<Long> blogIds = new Vector<Long>();
//            while (rs.next() && blogIds.size()<=10){
//                Long id = rs.getLong("blogId");
//                blogIds.add(id);
//            }
//            for (Long blogId:
//                    blogIds) {
//                Blog blog = instance.getInstance(Blog.class,"SELECT * FROM blogs WHERE blogId = ?",blogId);
//                BlogController.showBlog(blog);
//            }
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }catch (Exception e){
//
//        }
//    }
    /*private void fillMapResult(Map<String,String> result, Blog blog){
        result.put("blogId",String.valueOf(blog.getBlogId()));
        result.put("title", blog.getContent());
        result.put("time", String.valueOf(blog.getTime()));
        result.put("content",blog.getContent());
    }*/
}
