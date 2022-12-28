package service;

import model.Comment;
import model.PostList;
import java.util.ArrayList;
import java.util.List;

public class CommentService{
    NotificationService notificationService = new NotificationService();
    PostListService postListService = new PostListService();
    List<Comment>comments=new ArrayList<>();
    public boolean createComment(int userId,String text,int postId){
        Comment comment = new Comment();
        PostList post = (PostList) postListService.getById(postId);
        if (post!=null) {
            comment.setUserid(userId);
            comment.setComment(text);
            comment.setPostId(postId);
            notificationService.forComment(userId,post,text,post.getUserId());
            comments.add(comment);
            return true;
        }
        return false;
    }
    public void showComment(int postId){
        for (Comment c:comments) {
            if (c!=null){
                if (c.getPostId()==postId)
                    System.out.println(c.getComment());
            }
        }
    }
}
