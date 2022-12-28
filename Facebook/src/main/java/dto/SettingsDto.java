package dto;

import model.User;
import service.UserService;

import java.util.Scanner;

public class SettingsDto {
    static UserService userService=new UserService();
    public static void menuSetting(User user){
        while (true) {
            System.out.println("1.MyPosts   2.EditAccount   0.back");
            int select=new Scanner(System.in).nextInt();
            if (select==0) break;
            if (select==1);//post;
            if (select==2) userService.edit(user.getId());
        }
    }
}
