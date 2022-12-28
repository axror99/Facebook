package dto;

import service.NotificationService;
import service.UserService;

import java.util.Scanner;

public class NotificationDto {
    static Scanner scanner=new Scanner(System.in);
    static UserService userService=new UserService();
    static NotificationService notificationService=new NotificationService();
    public static void menuDto(int userId){
        while (true) {
            System.out.println("=====Notification (new ("+userService.getById(userId).isNewCount+"))=====");
            System.out.println("1.Like   2.Comment   3.NewPost   4.JoinGroup   0.back");
            int select = new Scanner(System.in).nextInt();
            switch (select){
                case 0 -> {
                    return;
                }
                case 1-> notificationService.showType("like",userId);
                case 2-> notificationService.showType("comment",userId);
                case 3-> notificationService.showType("post",userId);
                case 4-> {

                        notificationService.showType("join", userId);
                        System.out.println("0.back");
                        int choose = new Scanner(System.in).nextInt();
                        if (choose==0) break;
                        String groupName=notificationService.getGroupName(choose - 1, userId);
                        if (groupName!=null) notificationService.response(NotificationService.mapForGroupWithRequist,groupName,scanner,userService.getById(userId));

                }
            }
        }
    }
}
