package edu.whu.controller;

import edu.whu.entity.Blog;
import edu.whu.entity.User;
import edu.whu.service.MySQL.MySqlHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/blog")
public class BlogController {
    /**
     * 展示博客内容
     * @param blog 要展示的博客
     */
    public static void showBlog(Blog blog) {
        MessageController.hr();
        if(blog == null){
            MessageController.errorMessage("获取博客信息","该博客为空");
        }
        else{
            try{
                MySqlHelper instance = MySqlHelper.getInstance();
                ResultSet rs = instance.getResultSet("SELECT userName FROM users WHERE userID = ?",blog.getUserId());
                if(rs.next()){
                    // 通过字段检索
                    String name = rs.getString("userName");
                    System.out.println("***博客发布人："+name);
                }
                instance.closeResultSet();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            System.out.println("***博客内容：");
            System.out.println(blog.displayContent());
            System.out.println("***发布时间："+blog.getTime());
            System.out.println("***点赞数："+blog.getLike());
            System.out.println("***访问数："+blog.getVisit());
        }
        MessageController.hr();
    }

    /**
     * 展示该用户所有博客
     * @param user
     */
    @GetMapping("/getByUser/{userid}")
    public ResponseEntity<List<Blog>> showBlogs(@PathVariable int userid){
        // MessageController.message("用户 ".concat(user.getUserName()).concat(" 你好！以下是你所有发布的博客："));
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            ResultSet rs = instance.getResultSet("SELECT blogId FROM blogs WHERE userId = ?",userid);
            Vector<Long> blogIds = new Vector<Long>();
            while (rs.next()){
                Long id = rs.getLong("blogId");
                blogIds.add(id);
            }
            List<Blog> result = new ArrayList<>();
            for (Long blogId:
                 blogIds) {
                Blog blog = instance.getInstance(Blog.class,"SELECT * FROM blogs WHERE blogId = ?",blogId);
                result.add(blog);
                BlogController.showBlog(blog);
            }
            return ResponseEntity.ok(result);
        }catch (SQLException ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBlog(int userid, String userType, String content) {
        MySqlHelper instance = MySqlHelper.getInstance();
        instance.insertAndId("INSERT INTO blogs (userId, content, fromWho, time) VALUES (?, ?, ?, ?)", userid, content, userType, LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    /**
     * 从blog表中选取10条博客
     */
    public static void showBlogs(){
        MessageController.message("正在刷取博客：");
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            ResultSet rs = instance.getResultSet("SELECT blogId FROM blogs");
            Vector<Long> blogIds = new Vector<Long>();
            while (rs.next() && blogIds.size()<=10){
                Long id = rs.getLong("blogId");
                blogIds.add(id);
            }
            for (Long blogId:
                    blogIds) {
                Blog blog = instance.getInstance(Blog.class,"SELECT * FROM blogs WHERE blogId = ?",blogId);
                BlogController.showBlog(blog);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
