package dto;

import model.PostList;
import model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LentaDto {
    static Scanner scanner = new Scanner(System.in);
    static Scanner scannerint = new Scanner(System.in);

    public static PostList addList(User user,PostList postList) {
        int cut = 18;
        postList.setName(user.getName());
        postList.setUserId(user.getId());
        System.err.println("enter postName");
        postList.setPostName("   " + scanner.nextLine());
        System.out.println("Write.. ");
        String write = scanner.nextLine();
        StringBuilder a = new StringBuilder();
        for (int s = 0; s < write.length(); s++) {
            if (s != cut) {
                a.append(write.charAt(s));
            } else {
                a.append("\n" + write.charAt(s));
                cut += 18;
            }
        }
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String time1 = time.format(new Date());
        postList.setTime(time1);
        postList.setWord(a.toString());
        postList.setSize(postList.getSize() + 1);
        return postList;
    }

}
