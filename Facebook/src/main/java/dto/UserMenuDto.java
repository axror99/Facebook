package dto;

import model.User;
import service.UserService;

import java.util.Scanner;

public class UserMenuDto {
    static Scanner scanner = new Scanner(System.in);
    static UserService userService = new UserService();

    public static User sing_up(User user) {

        System.out.println("enter name: ");
        user.setName(scanner.nextLine());
        System.out.println("enter number: ");
        user.setPhoneNumber(scanner.nextLine());
        System.out.println("enter password: ");
        user.setPassword(scanner.nextLine());
        System.out.println("enter Email: ");
        user.setEmail(scanner.nextLine());
        System.out.println("enter userName: ");
        user.setUserName(scanner.nextLine());

        return user;
    }

    public static User signIn() {
        System.out.println("userName: ");
        String userName = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        User user = userService.chekingUser(userName, password);
        if (user == null) {
            System.out.println("Password or UserName is incorrect!");
            return null;
        }
        return user;
    }

}
