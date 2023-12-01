package edu.whu.controller;

import edu.whu.entity.Blog;
import edu.whu.entity.Comment;
import edu.whu.entity.User;
import edu.whu.service.MySQL.MySqlHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    //获取某个博客的所有评论
    @GetMapping("/showComments")
    public ResponseEntity<List<Comment>> showComments(Integer blogId) {
        // MessageController.message("用户 ".concat(user.getUserName()).concat(" 你好！以下是你所有发布的博客："));
        try{
            MySqlHelper instance = MySqlHelper.getInstance();
            ResultSet rs = instance.getResultSet("SELECT commentId FROM comments WHERE blogId = ?",blogId);
            Vector<Integer> commentIds = new Vector<Integer>();
            while (rs.next()){
                Integer id = rs.getInt("commentId");
                commentIds.add(id);
            }
            List<Comment> result = new ArrayList<>();
            for (Integer commentId:
                    commentIds) {
                Comment comment = instance.getInstance(Comment.class,"SELECT * FROM comments WHERE commentId = ?",commentId);
                result.add(comment);
                //BlogController.showBlog(blog);
            }
            return ResponseEntity.ok(result);
        }catch (SQLException ex){
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    //发布评论
    @PostMapping("/postComment")
    public ResponseEntity<Map<String , String>> postComment( String content) {
        Map<String , String> result = new HashMap<>();
        try {
            Comment comment = UserController.getLogInUser().postAndGetComment(content,UserController.getLogInUser().getReadingBlog().getBlogId());
            result.put("success","发布成功");
            result.put("commentId",String.valueOf(comment.getCommentId()));
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

    //删除评论
    @DeleteMapping("/deleteComment")
    public ResponseEntity<Map<String , String>> deleteComment(int commentId) {
        Map<String , String> result = new HashMap<>();
        try {
            MySqlHelper instance = MySqlHelper.getInstance();
            int affectedRows = instance.sqlCMD("DELETE FROM comments WHERE commentId = ?",commentId);
            if(affectedRows>0){//影响行数大于0，说明有操作
                result.put("success","删除成功");
                return ResponseEntity.ok(result);
            }
            else{
                result.put("error","删除失败，请检查commentId是否存在");
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
