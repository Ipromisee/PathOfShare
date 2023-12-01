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

    @PostMapping("/postBlog")
    public ResponseEntity<Map<String , String>> postBlog(int userId, String userType, String content , String title) {
        Map<String , String> result = new HashMap<>();
        try {
            MySqlHelper instance = MySqlHelper.getInstance();
            int blogId = instance.insertAndId("INSERT INTO blogs (userId, content, fromWho, time,title) VALUES (?, ?, ?, ?,?)", userId, content, userType, LocalDateTime.now(),title);
            result.put("success","发布成功");
            result.put("blogId",String.valueOf(blogId));
            return ResponseEntity.ok(result);
        }catch (SQLException e){
            result.put("error","数据库出现错误");
            ResponseEntity.badRequest().body(result);
        }catch (Exception e){
            result.put("error","类型判断错误，请检查type或fromWho");
            ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/deleteBlog")
    public ResponseEntity<Map<String , String>> deleteBlog(int blogId) {
        Map<String , String> result = new HashMap<>();
        try {
            MySqlHelper instance = MySqlHelper.getInstance();
            int affectedRows = instance.sqlCMD("DELETE FROM blogs WHERE blogId = ?",blogId);
            if(affectedRows>0){//影响行数大于0，说明有操作
                result.put("success","删除成功");
                return ResponseEntity.ok(result);
            }
            else{
                result.put("error","删除失败，请检查blogId是否存在");
                return ResponseEntity.ok(result);
            }
        }catch (SQLException e){
            result.put("error","数据库出现错误");
            ResponseEntity.badRequest().body(result);
        }catch (Exception e){
            result.put("error","类型判断错误，请检查type或fromWho");
            ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.badRequest().build();
    }
}
