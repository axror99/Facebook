package model;
import lombok.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
@Setter
@Getter
public class User extends Base {

    private boolean isNew;
    public int isNewCount=0;
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    Stack<Object> notification = new Stack<>();
    Map<Integer, Boolean> follower = new HashMap<>();

   public  User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id="+getId()+'\''+
                "name="+getName()+'\''+
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
